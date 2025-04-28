package com.zhul.erp.domain.mapper;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.compress.utils.Lists;

/**
 * Created by yanglikai on 2023/12/18.
 */
public class MapperExpand {

  public static <T, R> R map(T source, Function<T, R> mapper) {
    return Stream.of(source).map(mapper).findFirst().get();
  }

  public static <T, R> List<R> mapToList(List<T> source, Function<T, R> mapper) {
    if (CollectionUtil.isEmpty(source)) {
      return Lists.newArrayList();
    }

    return source.stream().map(mapper).collect(Collectors.toList());
  }

  public static <T, R> Page<R> mapToPage(Page<T> source, Function<T, R> mapper) {
    Objects.requireNonNull(source, "source must be not null");

    if (CollectionUtil.isEmpty(source.getRecords())) {
      return new Page<>();
    }

    Page<R> newPages = new Page<>();
    newPages.setTotal(source.getTotal());
    newPages.setSize(source.getSize());
    newPages.setCurrent(source.getCurrent());
    newPages.setPages(source.getPages());
    newPages.setRecords(source.getRecords().stream().map(mapper).collect(Collectors.toList()));

    return newPages;
  }
}
