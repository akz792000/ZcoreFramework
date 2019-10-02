package org.zcoreframework.batch.model;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/12/2017
 */

public class AggregationInfo {

    private Long totalCount;
    private Long minValue;
    private Long maxValue;

    public AggregationInfo() {
    }

    public AggregationInfo(Long minValue, Long maxValue, Long totalCount) {
        this.totalCount = totalCount;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
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
}
