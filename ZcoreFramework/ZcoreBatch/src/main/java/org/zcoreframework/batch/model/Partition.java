package org.zcoreframework.batch.model;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/12/2017
 */

public class Partition {

    private Long minValue;
    private Long maxValue;
    private Integer partitionNumber;

    public Partition() {
    }

    public Partition(Long minValue, Long maxValue, Integer partitionNumber) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.partitionNumber = partitionNumber;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getPartitionNumber() {
        return partitionNumber;
    }

    public void setPartitionNumber(Integer partitionNumber) {
        this.partitionNumber = partitionNumber;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append('{');
        builder.append("partitionNumber=").append(partitionNumber);
        builder.append(", minValue=").append(minValue);
        builder.append(", maxValue=").append(maxValue);
        builder.append('}');

        return builder.toString();
    }
}
