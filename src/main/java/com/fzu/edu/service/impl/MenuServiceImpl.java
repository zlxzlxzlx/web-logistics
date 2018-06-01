package com.fzu.edu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fzu.edu.dao.MenuMapper;
import com.fzu.edu.model.Menu;
import com.fzu.edu.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/27.
 */
@Service("menuService")
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements MenuService {
      @Resource
      private MenuMapper menuMapper;

      public List<HashMap> getAllTree(String menu){
          Map map = new HashMap();
          map.put("name",null);
          map.put("code",null);
          List<HashMap> treeList = menuMapper.selectAll(map);
          String[] str={};
          if (menu!=null){
              str = menu.split("/");
          }

          for (HashMap hashMap :treeList){
              for (int i = 0;i<str.length;i++){
                  if (hashMap.get("name").equals(str[i])){
                      hashMap.put("checked",true);
                      break;
                  }
                  else {
                      hashMap.put("checked",false);
                  }
              }
              if(hashMap.get("parent_code").equals("0")){//设置第一层展开
                  hashMap.put("$$isExpend",true);
              }
                for (HashMap hashMap1:treeList){
                     if (hashMap1.get("code").equals(hashMap.get("parent_code"))){
                          if (hashMap1.get("childItems")==null)
                          {
                              List childItems = new ArrayList();
                              childItems.add(hashMap);
                              hashMap1.put("childItems",childItems);
                          }else {
                              List childItems = (List) hashMap1.get("childItems");
                              childItems.add(hashMap);
                              hashMap1.put("childItems",childItems);
                          }
                          break;
                     }
                }
          }
          List resultList = new ArrayList();
          for(int i = 0; i<treeList.size(); i++){
              Map temp = treeList.get(i);
              if("0".equals(temp.get("parent_code").toString()))
                  resultList.add(temp);
          }

          return resultList;
      }
      public List<HashMap> getAll(String name,String code){
          Map map = new HashMap();
          map.put("name",name);
          map.put("code",code);
          List<HashMap> treeList = menuMapper.selectAll(map);
          return treeList;
      }
      public void addMenu(String name,String code,String parentCode,Integer id,Integer flag){
          if (id!=null){
              Menu menu = menuMapper.selectById(id);
              menu.setCode(code);
              menu.setName(name);
              if (flag==1){
                  menu.setFlag(1);
              }else {
                  menu.setFlag(0);
              }
              menuMapper.updateById(menu);
          }else {
              Menu menu = new Menu();
              menu.setCode(code);
              menu.setName(name);
              menu.setParentCode(parentCode);
              if (flag==1){
                  menu.setFlag(1);
              }else {
                  menu.setFlag(0);
              }
              menuMapper.insert1(menu);
          }
      }
      public  List<HashMap> isDelete(String code){
          return  menuMapper.isDelete(code);
      }
      public  List<HashMap> codeUnique(String code){
          return  menuMapper.codeUnique(code);
      }

}
