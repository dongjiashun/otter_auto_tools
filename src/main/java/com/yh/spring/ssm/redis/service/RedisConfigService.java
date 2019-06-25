//package com.yh.spring.ssm.redis.service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import com.yh.spring.ssm.redis.bean.RedisConstants;
//
///**
// * Created by zhangjia
// * Date:2017/8/16
// * Comment:Redis配置信息管理
// */
//@Service
//@CacheConfig(cacheNames = {"defaultCache"}, keyGenerator = "visionCacheKeyGenerator")
//public class RedisConfigService {
//
//    @Resource
//    private RedisTemplate<String, String> redisTemplate;
//
//   
//
//    /**
//     * 慢查询数据库白名单
//     * @return
//     */
//   @Cacheable(unless = "#result == null", value = { "" })
//    public Set<String> getSlowsqlDbWhitelist() {
//        Set<String> set = redisTemplate.opsForSet().members(RedisConstants.KEY_SLOWSQL_DB_WHITELIST);
//        return set == null ? new HashSet<>():set;
//    }
//
//    /**
//     * 慢SQL匹配白名单
//     *         Set<String> matchWhitelist = redisConfigService.getRdsSqlMatchWhitelist();
//            Set<String> equalWhitelist = redisConfigService.getRdsSqlEqualWhitelist();
//     * @return 111
//     */
//    @Cacheable(unless = "#result == null", value = { "" })
//    public Set<String> getRdsSqlMatchWhitelist() {
//        Set<String> set = redisTemplate.opsForSet().members(RedisConstants.KEY_SLOWSQL_SQL_MATCH_WHITELIST);
//        return set == null ? new HashSet<>():set;
//
//    }
//
//    /**
//     * 慢SQL比较白名单
//     *
//     * @return111
//     */
//    @Cacheable(unless = "#result == null", value = { "" })
//    public Set<String> getRdsSqlEqualWhitelist() {
//        Set<String> set = redisTemplate.opsForSet().members(RedisConstants.KEY_SLOWSQL_SQL_EQUAL_WHITELIST);
//        return set == null ? new HashSet<>():set;
//
//    }
//
//   
//
//}
