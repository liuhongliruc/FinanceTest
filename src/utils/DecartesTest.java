package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiongtian <xiongtian@kuaishou.com>
 * Created on 2022-05-09
 * //https://blog.csdn.net/weimingjue/article/details/101013282?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-101013282-blog-122472171.pc_relevant_antiscanv2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-101013282-blog-122472171.pc_relevant_antiscanv2&utm_relevant_index=2
 */
public class DecartesTest {
    public static void main(String[] args) {
        List<List<String>> listData = new ArrayList<>();
        listData.add(Arrays.asList("LA", "Brand"));
        listData.add(Arrays.asList("预付", "后付"));
        listData.add(Arrays.asList("修改信用账单", "不修改"));
        listData.add(Arrays.asList("全部回款", "部分回款", "未回款"));
        listData.add(Arrays.asList("代付回款", "不代付回款"));
        listData.add(Arrays.asList("回款撤销", "回款不撤销"));
        listData.add(Arrays.asList("取消信用账单","不取消信用账单"));
        List<List<String>> lisReturn = getDescartes(listData);
        System.out.println(lisReturn);
        System.out.println(lisReturn.size());
        for(int i=0;i<lisReturn.size();i++){
            System.out.println(lisReturn.get(i));

        }

    }

    private static <T> List<List<T>> getDescartes(List<List<T>> list) {
        List<List<T>> returnList = new ArrayList<>();
        descartesRecursive(list, 0, returnList, new ArrayList<T>());
        return returnList;
    }

    /**
     * 递归实现
     * 原理：从原始list的0开始依次遍历到最后
     *
     * @param originalList 原始list
     * @param position     当前递归在原始list的position
     * @param returnList   返回结果
     * @param cacheList    临时保存的list
     */
    private static <T> void descartesRecursive(List<List<T>> originalList, int position, List<List<T>> returnList, List<T> cacheList) {
        List<T> originalItemList = originalList.get(position);
        for (int i = 0; i < originalItemList.size(); i++) {
            //最后一个复用cacheList，节省内存
            List<T> childCacheList = (i == originalItemList.size() - 1) ? cacheList : new ArrayList<>(cacheList);
            childCacheList.add(originalItemList.get(i));
            if (position == originalList.size() - 1) {//遍历到最后退出递归
                returnList.add(childCacheList);
                continue;
            }
            descartesRecursive(originalList, position + 1, returnList, childCacheList);
        }
    }

}
