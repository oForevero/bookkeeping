package org.jeecg.modules.bookkeeping.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author LiKai
 * @Description 用于传输给 opt select 的 domain实体类
 * @Version 1.0.0
 * @Date 2023/6/16 15:37
 * @Created by LiKai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptSelectResult<T> {
    String label;
    List<T> options;
}
