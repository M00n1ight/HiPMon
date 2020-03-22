package com.hack.nipmon.problemsdetectorservice.repos;

import com.hack.nipmon.problemsdetectorservice.client.EuricaClient;
import com.hack.nipmon.problemsdetectorservice.domain.SensorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Repository
public class SensorConfigRepo {
    private Logger logger = LoggerFactory.getLogger(SensorConfigRepo.class);
    private Map<Integer, ThresholdValues> config;

    public ThresholdValues getThresholdValues(int id){
        return config.getOrDefault(id, NO_DATA_THRESHOLD);
    }

    private static final ThresholdValues NO_DATA_THRESHOLD = new ThresholdValues(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY);

    @Autowired
    private EuricaClient euricaClient;

    public void reloadRepo() {
        Map<Integer, ThresholdValues> newConfig = new HashMap<>();

        List<SensorConfig> configs = euricaClient.getConfigs();

        for (SensorConfig i : configs) {
            logger.error(i.toString());
            newConfig.put(
                    i.getId(),
                    new ThresholdValues(i.getTopThreshold(), i.getBottomThreshold())
            );
        }

        config = newConfig;
    }

    public static class ThresholdValues {
        private final float top;
        private final float bottom;

        private ThresholdValues(Float top, Float bottom){
            this.top    = top    != null ? top      : Float.POSITIVE_INFINITY;
            this.bottom = bottom != null ? bottom   : Float.NEGATIVE_INFINITY;
        }

        public float getTop() { return top; }

        public float getBottom() { return bottom; }

        @Override
        public String toString() {
            return new StringJoiner(", ", ThresholdValues.class.getSimpleName() + "[", "]")
                    .add("top=" + top)
                    .add("bottom=" + bottom)
                    .toString();
        }
    }
}
