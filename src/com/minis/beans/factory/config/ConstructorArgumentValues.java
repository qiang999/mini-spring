package com.minis.beans.factory.config;

import java.util.*;

/**
 * @Title: ArgumentValues
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2023/7/11 - 14:16
 */
public class ConstructorArgumentValues {
    private final List<ConstructorArgumentValue> argumentValueList = new ArrayList<>();

    public ConstructorArgumentValues(){}

    public void addArgumentValue(ConstructorArgumentValue argumentValue){
        this.argumentValueList.add(argumentValue);
    }

    public ConstructorArgumentValue getIndexedArgumentValue(int index){
        ConstructorArgumentValue argumentValue = this.argumentValueList.get(index);
        return argumentValue;
    }

    public int getArgumentValueCount(){
        return (this.argumentValueList.size());
    }

    public boolean isEmpty(){
        return (this.argumentValueList.isEmpty());
    }
//    private final Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>(0);
//    private final List<ArgumentValue> genericArgumentValues = new LinkedList<>();
//    public ArgumentValues(){}
//
//    private void addArgumentValue(Integer key, ArgumentValue newValue){
//        this.indexedArgumentValues.put(key, newValue);
//    }
//
//    public boolean hasIndexedArgumentValue(int index){
//        return this.indexedArgumentValues.containsKey(index);
//    }
//
//    public ArgumentValue getIndexedArgumentValue(int index){
//        return this.indexedArgumentValues.get(index);
//    }
//
//    public void addGenericArgumentValue(Object value, String type){
//        this.genericArgumentValues.add(new ArgumentValue(value, type));
//    }
//
//    private void addGenericArgumentValue(ArgumentValue newValue){
//        if (newValue.getName() != null){
//            for (Iterator<ArgumentValue> it = this.genericArgumentValues.iterator(); it.hasNext();) {
//                ArgumentValue currentValue = it.next();
//                if (newValue.getName().equals(currentValue.getName())){
//                    it.remove();
//                }
//            }
//        }
//        this.genericArgumentValues.add(newValue);
//    }
//
//    public ArgumentValue getaddGenericArgumentValue(String requiredName){
//        for (ArgumentValue valueHolder : this.genericArgumentValues){
//            if (valueHolder.getName() != null && (requiredName == null || !valueHolder.getName().equals(requiredName))){
//                continue;
//            }
//            return valueHolder;
//        }
//        return null;
//    }
//
//    public int getArgumentCount(){
//        return this.genericArgumentValues.size();
//    }
//
//    public boolean isEmpty(){
//        return this.genericArgumentValues.isEmpty();
//    }
}
