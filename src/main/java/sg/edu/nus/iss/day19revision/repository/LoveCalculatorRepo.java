package sg.edu.nus.iss.day19revision.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day19revision.model.LoveCalculator;

@Repository
public class LoveCalculatorRepo {
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final String RECORD = "record";

    public void save(LoveCalculator lc) {
        redisTemplate.opsForList().leftPush(RECORD, lc);
    }

    public List<LoveCalculator> getRecord(){
        List<LoveCalculator> lcRecord = new LinkedList<>();
        long recordSize = redisTemplate.opsForList().size(RECORD);

        // List<Object> fromLcRecord = redisTemplate.opsForList()
        //     .range(RECORD, 0, (int) recordSize-1);

        lcRecord = redisTemplate.opsForList().range(RECORD, 0, (int) recordSize-1)
            .stream()
            .filter(LoveCalculator.class::isInstance)
            .map(LoveCalculator.class::cast)
            .toList();

        return lcRecord;
    }

}
