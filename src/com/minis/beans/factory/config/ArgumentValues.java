package com.minis.beans.factory.config;

import java.util.*;

/**
 * @Title: ArgumentValues
 * @Package: com.minis.beans
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/7 - 17:18
 */
public class ArgumentValues {

    //private final Map<Integer,ArgumentValue> integerArgumentValues = new HashMap<>(0);

    private final List<ArgumentValue> genericArgumentValues = new ArrayList<>();

    public ArgumentValues(){}

    public void addArgumentValue(ArgumentValue argumentValue){
        genericArgumentValues.add(argumentValue);
    }

    public ArgumentValue getIndexedArgumentValue(int index){
        ArgumentValue argumentValue = genericArgumentValues.get(index);
        return argumentValue;
    }

//    private void addArgumentValue(Integer key, ArgumentValue argumentValue){
//        this.integerArgumentValues.put(key, argumentValue);
//    }
//
//    public boolean hasIntegerArgumentValue(Integer key){
//        return this.integerArgumentValues.containsKey(key);
//    }
//
//    public ArgumentValue getIndexedArgumentValue(Integer key){
//        return this.integerArgumentValues.get(key);
//    }

//    public void addGenericArgumentValue(Object value,String type){
//        this.genericArgumentValues.add(new ArgumentValue(value,type));
//    }

//    private void addGenericArgumentValue(ArgumentValue argumentValue){
//        if (argumentValue.getName() != null){
//            for (Iterator<ArgumentValue> it = this.genericArgumentValues.iterator(); it.hasNext();){
//                ArgumentValue argument = it.next();
//                if (argument.getName().equals(argumentValue.getName())){
//                    it.remove();
//                }
//            }
//        }
//        this.genericArgumentValues.add(argumentValue);
//    }
//
//    public ArgumentValue getGenericArgumentValue(String requiredName){
//        for(ArgumentValue argumentValue : this.genericArgumentValues){
//            if (argumentValue.getName()!=null&&(requiredName==null||!argumentValue.getName().equals(requiredName))){
//                continue;
//            }
//            return argumentValue;
//        }
//        return null;
//    }

    public int getArgumentCount(){
        return this.genericArgumentValues.size();
    }

    public boolean isEmpty(){
        return this.genericArgumentValues.isEmpty();
    }
}
