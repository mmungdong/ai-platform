package com.platform.utils;

import org.springframework.stereotype.Component;

/**
 * @author MungDong
 * @create 2024-03-23-15:33
 */
@Component
public class SnowflakeIdGenerator {

    private final long dataCenterId;
    private final long machineId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private final long twepoch = 1288834974657L;
    private final long dataCenterIdBits = 5L;
    private final long machineIdBits = 5L;
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    private final long maxMachineId = -1L ^ (-1L << machineIdBits);
    private final long sequenceBits = 12L;
    private final long machineIdShift = sequenceBits;
    private final long dataCenterIdShift = sequenceBits + machineIdBits;
    private final long timestampLeftShift = sequenceBits + machineIdBits + dataCenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    public SnowflakeIdGenerator() {

        // TODO: set value by env
        long dataCenterId = 1;
        long machineId = 1;


        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException("Data Center ID can't be greater than " + maxDataCenterId + " or less than 0");
        }
        if (machineId > maxMachineId || machineId < 0) {
            throw new IllegalArgumentException("Machine ID can't be greater than " + maxMachineId + " or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    public synchronized long generateId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (machineId << machineIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

}