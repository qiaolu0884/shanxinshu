package com.shan.org.shan.pojo.sys;

import java.util.ArrayList;
import java.util.List;

public class SysuserGroupQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public SysuserGroupQuery() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSysuserIdIsNull() {
            addCriterion("sysuser_id is null");
            return (Criteria) this;
        }

        public Criteria andSysuserIdIsNotNull() {
            addCriterion("sysuser_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysuserIdEqualTo(Long value) {
            addCriterion("sysuser_id =", value, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdNotEqualTo(Long value) {
            addCriterion("sysuser_id <>", value, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdGreaterThan(Long value) {
            addCriterion("sysuser_id >", value, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sysuser_id >=", value, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdLessThan(Long value) {
            addCriterion("sysuser_id <", value, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdLessThanOrEqualTo(Long value) {
            addCriterion("sysuser_id <=", value, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdIn(List<Long> values) {
            addCriterion("sysuser_id in", values, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdNotIn(List<Long> values) {
            addCriterion("sysuser_id not in", values, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdBetween(Long value1, Long value2) {
            addCriterion("sysuser_id between", value1, value2, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysuserIdNotBetween(Long value1, Long value2) {
            addCriterion("sysuser_id not between", value1, value2, "sysuserId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdIsNull() {
            addCriterion("sysgroup_id is null");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdIsNotNull() {
            addCriterion("sysgroup_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdEqualTo(Long value) {
            addCriterion("sysgroup_id =", value, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdNotEqualTo(Long value) {
            addCriterion("sysgroup_id <>", value, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdGreaterThan(Long value) {
            addCriterion("sysgroup_id >", value, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sysgroup_id >=", value, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdLessThan(Long value) {
            addCriterion("sysgroup_id <", value, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdLessThanOrEqualTo(Long value) {
            addCriterion("sysgroup_id <=", value, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdIn(List<Long> values) {
            addCriterion("sysgroup_id in", values, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdNotIn(List<Long> values) {
            addCriterion("sysgroup_id not in", values, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdBetween(Long value1, Long value2) {
            addCriterion("sysgroup_id between", value1, value2, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andSysgroupIdNotBetween(Long value1, Long value2) {
            addCriterion("sysgroup_id not between", value1, value2, "sysgroupId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}