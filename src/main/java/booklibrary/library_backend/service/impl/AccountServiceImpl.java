package booklibrary.library_backend.service.impl;

import booklibrary.library_backend.entity.database_obj.Account;
import booklibrary.library_backend.entity.view_obj.request.ConfirmRestViewObj;
import booklibrary.library_backend.entity.view_obj.request.EmailRegisterViewObj;
import booklibrary.library_backend.entity.view_obj.request.EmailResetViewObj;
import booklibrary.library_backend.mapper.AccountMapper;
import booklibrary.library_backend.service.AccountService;
import booklibrary.library_backend.utils.Const;
import booklibrary.library_backend.utils.FlowUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    AmqpTemplate amqpTemplate;
    @Resource
    FlowUtils flowUtils;
    @Resource
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByNameOrEmail(username);
        if (account == null)
            throw new UsernameNotFoundException("用户名或密码错误");
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    @Override
    public Account findAccountByNameOrEmail(String Text) {
        return this.query()
                .eq("username", Text).or()
                .eq("email", Text)
                .one();
    }

    @Override
    public String registerEmailVerifyCode(String Type, String Email, String IP) {
        synchronized (IP.intern()) {
            if (!this.verifyLimit(IP))
                return "请求频繁，请稍后再试！";
            Random random = new Random();
            int Code = random.nextInt(899999) + 100000;
            Map<String, Object> data = Map.of("type", Type, "email", Email, "code", Code);
            amqpTemplate.convertAndSend("mail", data);
            stringRedisTemplate.opsForValue().
                    set(Const.VERIFY_EMAIL_DATA + Email, String.valueOf(Code), 5, TimeUnit.MINUTES);
            return null;
        }
    }

    @Override
    public String registerEmailAccount(EmailRegisterViewObj obj) {
        String email = obj.getEmail();
        String code = stringRedisTemplate.opsForValue().get(Const.VERIFY_EMAIL_DATA + email);
        if (code == null) return "请先获取验证码";
        if (!code.equals(obj.getCode())) return "验证码输入错误，请重新输入";
        if (this.exitsAccountByEmail(email)) return "此邮件已被其他用户注册";
        if (this.exitsAccountByUsername(obj.getUsername())) return "此用户名已被注册";
        String password = passwordEncoder.encode(obj.getPassword());
        Account account = new Account(null, obj.getUsername(), password, email, "user", new Date());
        if (this.save(account)) {
            stringRedisTemplate.delete(Const.VERIFY_EMAIL_DATA + email);
            return null;
        } else
            return "内部错误，请联系管理员";
    }

    @Override
    public String resetConfirm(ConfirmRestViewObj obj) {
        String email = obj.getEmail();
        String code = stringRedisTemplate.opsForValue().get(Const.VERIFY_EMAIL_DATA + email);
        if (code == null) return "请先获取验证码";
        if (!code.equals(obj.getCode())) return "验证码有误，请重新输入";
        return null;
    }

    @Override
    public String resetEmailAccountPassword(EmailResetViewObj obj) {
        String email = obj.getEmail();
        String verify = this.resetConfirm(new ConfirmRestViewObj(email,obj.getCode()));
        if (verify != null) return verify;
        String password = passwordEncoder.encode(obj.getPassword());
        boolean update = this.update().eq("email", email).set("password", password).update();
        if (update) stringRedisTemplate.delete(Const.VERIFY_EMAIL_DATA + email);
        return null;
    }

    private boolean verifyLimit(String IP) {
        String key = Const.VERIFY_EMAIL_LIMIT + IP;
        return flowUtils.limitCheck(key, 60);
    }

    private boolean exitsAccountByEmail(String email) {
        return this.baseMapper.exists(Wrappers.<Account>query().eq("email", email));
    }

    private boolean exitsAccountByUsername(String username) {
        return this.baseMapper.exists(Wrappers.<Account>query().eq("username", username));
    }
}
