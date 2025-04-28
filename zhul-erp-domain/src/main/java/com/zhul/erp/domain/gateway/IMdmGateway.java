package com.zhul.erp.domain.gateway;

import com.zhul.cloud.common.model.Keyvalue;
import java.util.List;

/**
 * Created by yanglikai on 2023/12/19.
 */
public interface IMdmGateway {

  List<Keyvalue> loadAll4Dept();

  List<Keyvalue> loadAll4Role();

  List<Keyvalue> loadAll4User();

  List<String> loadRoleCodes(List<Integer> ids);

  List<String> loadOrgCodes(List<Integer> ids);
}
