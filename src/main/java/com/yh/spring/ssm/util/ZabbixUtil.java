package com.yh.spring.ssm.util;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;



import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * zabbix Api
 * @author dongjs
 */
public class ZabbixUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZabbixUtil.class);

    private ZabbixApi zabbixApi;

    public ZabbixUtil(String username, String password, String url) throws Exception {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(url)){
            throw new Exception("ZabbixApi初始化失败！参数不全！");
        }
        login(username, password, url);
    }

    private ZabbixApi login(String username, String password, String url) throws Exception {
        zabbixApi = new DefaultZabbixApi(url);
        zabbixApi.init();
        boolean login = zabbixApi.login(username, password);
        if(!login){
        	System.out.println(username + " login in Zabbix " + (login ? "SUCCESS" : "FALURE") + " !");
            LOGGER.info(username + " login in Zabbix " + (login ? "SUCCESS" : "FALURE") + " !");
        }
        return zabbixApi;
    }

    /**
     * 获取zabbix中所以的主机群组列表
     * @return 主机群组列表json
     */
    public String getHostGroupList() throws Exception {
        Request request = RequestBuilder.newBuilder().method("hostgroup.get")
                .paramEntry("output", "extend")
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        System.out.println(result);
        return result.toJSONString();
    }
    
    public String getHostList() throws Exception {
        Request request = RequestBuilder.newBuilder().method("host.get")
                .paramEntry("output", new String[]{"host", "name", "description", "hostid"})
                .paramEntry("selectGroups", "extend")
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        System.out.println(result);
        return result.toJSONString();
    }

    /**
     * 获取主机ID
     * @param hostIp
     * @return 主机ID
     */
    public String getHostByGroupid(Integer groupid) throws Exception {
        Request request = RequestBuilder.newBuilder().method("host.get")
                .paramEntry("groupids", groupid)
                .paramEntry("output", new String[]{"host", "name", "description", "hostid"})
                .paramEntry("selectGroups", "extend")
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }

    /**
     * 获取zabbix报警列表
     * @param timeFrom 仅返回在给定时间之后生成的警报。
     * @return
     */
    public String getAlertList(Long timeFrom) throws Exception {
        Request request = RequestBuilder.newBuilder().method("alert.get")
                .paramEntry("output", new String[]{"sendto", "subject", "clock", "message"})
                .paramEntry("selectHosts", new String[]{"host"})
                .paramEntry("time_from", timeFrom)
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }

    /**
     * 获取zabbix报警列表
     * @param timeFrom 仅返回在给定时间之后生成的警报。
     * @return
     */
    public String getAlertListByGroupids(String groupid, String timeFrom) throws Exception {
        Request request = RequestBuilder.newBuilder().method("alert.get")
                .paramEntry("time_from", timeFrom)
                .paramEntry("groupids", groupid)
                .paramEntry("output", new String[]{"sendto", "subject", "clock", "message","triggerid"})
//                .paramEntry("output","extend" )
//                .paramEntry("selectHosts", new String[]{"host"})
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
//        System.out.println(result.toJSONString());
        return result.toJSONString();
    }

    /**
     * 获取zabbix最近问题列表
     * @return
     * @throws Exception
     */
    public String getTriggerInfoList() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("value", 1);
        jo.put("priority", new String[]{"2", "3", "4", "5"});
        Request request = RequestBuilder.newBuilder().method("trigger.get")
                .paramEntry("output", new String[]{"description", "priority", "lastchange"})
                .paramEntry("selectHosts", new String[]{"host", "name", "hostid"})
                .paramEntry("selectDependencies", "extend")
                .paramEntry("expandData", "host")
                .paramEntry("skipDependent", "1")
                .paramEntry("monitored", "1")
                .paramEntry("active", "1")
                .paramEntry("expandDescription", "1")
                .paramEntry("sortfield", "priority")
                .paramEntry("sortorder", "DESC")
                .paramEntry("filter", jo)
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }
    
    /**
     * 根据群组id和机器host获取触发器信息列表
     * @param groupid
     * @param host
     * @return
     * @throws Exception
     */
    public String getTrigger(Integer groupid, String host) throws Exception {
        Request request = RequestBuilder.newBuilder().method("trigger.get")
                .paramEntry("groupids", groupid)
                .paramEntry("host", host)
                .paramEntry("monitored", 1)
                .paramEntry("output", new String[]{"expression","description", "priority", "lastchange","status"})
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }
    
    /**
     * 根据触发器id获取触发器信息
     * @param triggerId
     * @return
     * @throws Exception
     */
    public String getTriggerByTriggerId(Integer triggerId) throws Exception {
        Request request = RequestBuilder.newBuilder().method("trigger.get")
                .paramEntry("triggerids", triggerId)
                .paramEntry("output", "extend")
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }

    public String getItemList() throws Exception {
        Request request = RequestBuilder.newBuilder().method("item.get").paramEntry("output", "extend").paramEntry("monitored", "true").build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }

    public String getTriggerPrototypeByGroupid(Integer groupid) throws Exception {
        Request request = RequestBuilder.newBuilder().method("triggerprototype.get")
                .paramEntry("groupids", groupid)
                .paramEntry("selectHosts", new String[]{"host", "hostid"})
                .paramEntry("selectGroups", "extend")
                .paramEntry("output", new String[]{"expression", "triggerid", "description", "priority", "status"})
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }
    
    public String getTriggerPrototypeByTriggerids(Integer triggerid) throws Exception {
        Request request = RequestBuilder.newBuilder().method("triggerprototype.get")
                .paramEntry("triggerids", triggerid)
                .paramEntry("output", "extend")
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }
    
    public String getTriggerInfo(Integer groupid,Long lastChangeSince) throws Exception {
        Request request = RequestBuilder.newBuilder().method("trigger.get")
                .paramEntry("groupids", groupid)
                .paramEntry("lastChangeSince", lastChangeSince)
                .paramEntry("output", new String[]{"description", "priority", "lastchange"})
                .paramEntry("selectHosts", new String[]{"host", "name", "hostid"})
                .paramEntry("skipDependent", "1")
                .paramEntry("monitored", "1")
                .paramEntry("active", "1")
                .paramEntry("expandDescription", "1")
                .paramEntry("sortfield", "priority")
                .build();
        JSONObject response = zabbixRequest(request);
        zabbixError(response);
        JSONArray result = response.getJSONArray("result");
        return result.toJSONString();
    }

    private JSONObject zabbixRequest(Request request) throws Exception {
        JSONObject response = zabbixApi.call(request);
        return response;
    }

    private void zabbixError(JSONObject response) throws Exception {
        if (!StringUtils.isBlank(response.getString("error")))
            throw new Exception("向Zabbix请求出错了！" + JSON.parseObject(response.getString("error")).getString("data"));
    }

    
    
}