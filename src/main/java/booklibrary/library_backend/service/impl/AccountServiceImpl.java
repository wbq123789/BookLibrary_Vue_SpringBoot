package booklibrary.library_backend.service.impl;

import booklibrary.library_backend.entity.database_obj.Account;
import booklibrary.library_backend.mapper.AccountMapper;
import booklibrary.library_backend.service.AccountService;
import booklibrary.library_backend.utils.Const;
import booklibrary.library_backend.utils.FlowUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account=this.findAccountByNameOrEmail(username);
        if (account==null)
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
                .eq("username",Text).or()
                .eq("email",Text)
                .one();
    }

    @Override
    public String registerEmailVerifyCode(String Type, String Email, String IP) {
        synchronized (IP.intern()){
            if(!this.verifyLimit(IP))
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

    private boolean verifyLimit(String IP){
        String key=Const.VERIFY_EMAIL_LIMIT+IP;
        return flowUtils.limitCheck(key,60);
    }
}
