package com.kisin.gen.common.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-11-19 15:35
 * @Description:
 */
public class DataMapperStore {

    private List<DataMapper> mapperList;

    private static DataMapperStore instance = new DataMapperStore();

    public DataMapperStore() {
        this.mapperList = new ArrayList<>();
    }

    public static DataMapperStore getInstance(){
        return instance;
    }

    public DataMapper addMappers(DataMapper mapper) {
        this.mapperList.add(mapper);
        return mapper;
    }

    public DataMapper newDataMapper() {
        return new DataMapper();
    }

    public DataMapperFuns newDataMapperFuns() {
        return new DataMapperFuns();
    }

    public DataMapperFunsParam newDataMapperFunsParam() {
        return new DataMapperFunsParam();
    }

    public class DataMapper implements Serializable {
        private String className;
        private String simpleName;
        private List<DataMapperFuns> funsList;

        public DataMapperFuns addFuns(DataMapperFuns funs) {
            if(this.funsList == null) this.funsList = new ArrayList<>();
            this.funsList.add(funs);
            return funs;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getSimpleName() {
            return simpleName;
        }

        public void setSimpleName(String simpleName) {
            this.simpleName = simpleName;
        }

        public List<DataMapperFuns> getFunsList() {
            return funsList;
        }

        public void setFunsList(List<DataMapperFuns> funsList) {
            this.funsList = funsList;
        }
    }

    public class DataMapperFuns implements Serializable{
        private String methodName;
        private String returnTypeClassName;
        private String returnTypeSimpleName;
        private List<DataMapperFunsParam> paramList;

        public DataMapperFuns addParam(DataMapperFunsParam param) {
            if(this.paramList==null) this.paramList = new ArrayList<>();
            this.paramList.add(param);
            return this;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public String getReturnTypeClassName() {
            return returnTypeClassName;
        }

        public void setReturnTypeClassName(String returnTypeClassName) {
            this.returnTypeClassName = returnTypeClassName;
        }

        public String getReturnTypeSimpleName() {
            return returnTypeSimpleName;
        }

        public void setReturnTypeSimpleName(String returnTypeSimpleName) {
            this.returnTypeSimpleName = returnTypeSimpleName;
        }

        public List<DataMapperFunsParam> getParamList() {
            return paramList;
        }

        public void setParamList(List<DataMapperFunsParam> paramList) {
            this.paramList = paramList;
        }
    }

    public class DataMapperFunsParam implements Serializable{
        private String paramTypeClassName;
        private String paramTypeSimpleName;
        private String paramName;

        public String getParamTypeClassName() {
            return paramTypeClassName;
        }

        public void setParamTypeClassName(String paramTypeClassName) {
            this.paramTypeClassName = paramTypeClassName;
        }

        public String getParamTypeSimpleName() {
            return paramTypeSimpleName;
        }

        public void setParamTypeSimpleName(String paramTypeSimpleName) {
            this.paramTypeSimpleName = paramTypeSimpleName;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }
    }
}
