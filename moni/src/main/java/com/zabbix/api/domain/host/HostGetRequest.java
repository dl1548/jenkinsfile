package com.zabbix.api.domain.host;

import java.util.ArrayList;
import java.util.List;

import com.zabbix.api.domain.base.RequestBase;

public class HostGetRequest extends RequestBase {
	public HostGetRequest(String auth) {
		super("host.get",auth);
	}

	private HostGetParams params = new HostGetParams();

	public void setParams(HostGetParams params) {
		this.params = params;
	}

	public HostGetParams getParams() {
		return params;
	}

	public static class HostGetParams {
		private String[] groupids;
		private String[] applicationids;
		private String[] dseviceids;
		private String[] gaphids;
		private String[] hostids;
		private String[] httptestids;
		private String[] intefaceids;
		private String[] itemids;
		private String[] maintenanceids;
		private Boolean monitored_hosts;
		private Boolean proxy_hosts;
		private String[] proxyids;
		private String[] templateids;
		private String[] tiggeids;
		private Boolean templated_hosts;
		private String with_items;
		private String with_applications;

		public Boolean getTemplated_hosts() {
			return templated_hosts;
		}

		public void setTemplated_hosts(Boolean templated_hosts) {
			this.templated_hosts = templated_hosts;
		}

		private String with_gaphs;
		private String with_histoical_items;
		private String with_httptests;
		private String with_monitoed_httptests;
		private String with_monitoed_items;
		private String with_monitoed_tigges;
		private String with_simple_gaph_items;
		private String with_tigges;
		private String withInventoy;
		private String selectGroups;
		private String selectApplications;
		private String selectDiscoveies;
		private String selectGaphs;
		private String selectInterfaces;
		private String selectInventoy;
		private String selectItems;
		private String selectMacros;
		private String selectParentTemplates;
		private String selectSceens;
		private String selectTriggers;
		// private Object filte;
		private String limitSelects;
		private String[] sortfield;
		private String countOutput;
		private String editable;
		private String excludeSeach;
		private String limit;
		private String[] nodeids;
		private String output;
		private String pesevekeys;

		public Boolean getMonitored_hosts() {
			return monitored_hosts;
		}

		public void setMonitored_hosts(Boolean monitored_hosts) {
			this.monitored_hosts = monitored_hosts;
		}

		public Boolean getProxy_hosts() {
			return proxy_hosts;
		}

		public void setProxy_hosts(Boolean proxy_hosts) {
			this.proxy_hosts = proxy_hosts;
		}

		public String getSelectInterfaces() {
			return selectInterfaces;
		}

		public void setSelectInterfaces(String selectInterfaces) {
			this.selectInterfaces = selectInterfaces;
		}

		public String getSelectParentTemplates() {
			return selectParentTemplates;
		}

		public void setSelectParentTemplates(String selectParentTemplates) {
			this.selectParentTemplates = selectParentTemplates;
		}

		private String seach;
		private String seachByAny;
		private String seachWildcadsEnabled;
		private String[] sortorder;
		private String statSeach;

		// private HostFilter filter = new HostFilter();

		public static class HostFilter {
			private List host;
			private List ip;
			private List<String> name;

			public List getHost() {
				return host;
			}

			public void setHost(List host) {
				this.host = host;
			}

			public List getIp() {
				return ip;
			}

			public void setIp(List ip) {
				this.ip = ip;
			}

			public void setName(List<String> name) {
				this.name = name;
			}

			public List<String> getName() {
				if (name == null) {
					name = new ArrayList<String>();
				}

				return name;
			}
		}

		public String[] getGroupids() {
			return groupids;
		}

		public void setGroupids(String[] groupids) {
			this.groupids = groupids;
		}

		public String[] getApplicationids() {
			return applicationids;
		}

		public void setApplicationids(String[] applicationids) {
			this.applicationids = applicationids;
		}

		public String[] getDseviceids() {
			return dseviceids;
		}

		public void setDseviceids(String[] dseviceids) {
			this.dseviceids = dseviceids;
		}

		public String[] getGaphids() {
			return gaphids;
		}

		public void setGaphids(String[] gaphids) {
			this.gaphids = gaphids;
		}

		public String[] getHostids() {
			return hostids;
		}

		public void setHostids(String[] hostids) {
			this.hostids = hostids;
		}

		public String[] getHttptestids() {
			return httptestids;
		}

		public void setHttptestids(String[] httptestids) {
			this.httptestids = httptestids;
		}

		public String[] getIntefaceids() {
			return intefaceids;
		}

		public void setIntefaceids(String[] intefaceids) {
			this.intefaceids = intefaceids;
		}

		public String[] getItemids() {
			return itemids;
		}

		public void setItemids(String[] itemids) {
			this.itemids = itemids;
		}

		public String[] getMaintenanceids() {
			return maintenanceids;
		}

		public void setMaintenanceids(String[] maintenanceids) {
			this.maintenanceids = maintenanceids;
		}

		public String[] getTemplateids() {
			return templateids;
		}

		public void setTemplateids(String[] templateids) {
			this.templateids = templateids;
		}

		public String[] getTiggeids() {
			return tiggeids;
		}

		public void setTiggeids(String[] tiggeids) {
			this.tiggeids = tiggeids;
		}

		public void setWith_items(String with_items) {
			this.with_items = with_items;
		}

		public String getWith_items() {
			return with_items;
		}

		public void setWith_applications(String with_applications) {
			this.with_applications = with_applications;
		}

		public String getWith_applications() {
			return with_applications;
		}

		public void setWith_gaphs(String with_gaphs) {
			this.with_gaphs = with_gaphs;
		}

		public String getWith_gaphs() {
			return with_gaphs;
		}

		public void setWith_histoical_items(String with_histoical_items) {
			this.with_histoical_items = with_histoical_items;
		}

		public String getWith_histoical_items() {
			return with_histoical_items;
		}

		public void setWith_httptests(String with_httptests) {
			this.with_httptests = with_httptests;
		}

		public String getWith_httptests() {
			return with_httptests;
		}

		public void setWith_monitoed_httptests(String with_monitoed_httptests) {
			this.with_monitoed_httptests = with_monitoed_httptests;
		}

		public String getWith_monitoed_httptests() {
			return with_monitoed_httptests;
		}

		public void setWith_monitoed_items(String with_monitoed_items) {
			this.with_monitoed_items = with_monitoed_items;
		}

		public String getWith_monitoed_items() {
			return with_monitoed_items;
		}

		public void setWith_monitoed_tigges(String with_monitoed_tigges) {
			this.with_monitoed_tigges = with_monitoed_tigges;
		}

		public String getWith_monitoed_tigges() {
			return with_monitoed_tigges;
		}

		public void setWith_simple_gaph_items(String with_simple_gaph_items) {
			this.with_simple_gaph_items = with_simple_gaph_items;
		}

		public String getWith_simple_gaph_items() {
			return with_simple_gaph_items;
		}

		public void setWith_tigges(String with_tigges) {
			this.with_tigges = with_tigges;
		}

		public String getWith_tigges() {
			return with_tigges;
		}

		public void setWithInventoy(String withInventoy) {
			this.withInventoy = withInventoy;
		}

		public String getWithInventoy() {
			return withInventoy;
		}

		public String getSelectGroups() {
			return selectGroups;
		}

		public void setSelectGroups(String selectGroups) {
			this.selectGroups = selectGroups;
		}

		public void setSelectApplications(String selectApplications) {
			this.selectApplications = selectApplications;
		}

		public String getSelectApplications() {
			return selectApplications;
		}

		public void setSelectDiscoveies(String selectDiscoveies) {
			this.selectDiscoveies = selectDiscoveies;
		}

		public String getSelectDiscoveies() {
			return selectDiscoveies;
		}

		public void setSelectGaphs(String selectGaphs) {
			this.selectGaphs = selectGaphs;
		}

		public String getSelectGaphs() {
			return selectGaphs;
		}

		public void setSelectInventoy(String selectInventoy) {
			this.selectInventoy = selectInventoy;
		}

		public String getSelectInventoy() {
			return selectInventoy;
		}

		public void setSelectItems(String selectItems) {
			this.selectItems = selectItems;
		}

		public String getSelectItems() {
			return selectItems;
		}

		public String getSelectMacros() {
			return selectMacros;
		}

		public void setSelectMacros(String selectMacros) {
			this.selectMacros = selectMacros;
		}

		public void setSelectSceens(String selectSceens) {
			this.selectSceens = selectSceens;
		}

		public String getSelectSceens() {
			return selectSceens;
		}

		public String getSelectTriggers() {
			return selectTriggers;
		}

		public void setSelectTriggers(String selectTriggers) {
			this.selectTriggers = selectTriggers;
		}

		public void setLimitSelects(String limitSelects) {
			this.limitSelects = limitSelects;
		}

		public String getLimitSelects() {
			return limitSelects;
		}

		public void setCountOutput(String countOutput) {
			this.countOutput = countOutput;
		}

		public String getCountOutput() {
			return countOutput;
		}

		public void setEditable(String editable) {
			this.editable = editable;
		}

		public String getEditable() {
			return editable;
		}

		public void setExcludeSeach(String excludeSeach) {
			this.excludeSeach = excludeSeach;
		}

		public String getExcludeSeach() {
			return excludeSeach;
		}

		public void setLimit(String limit) {
			this.limit = limit;
		}

		public String getLimit() {
			return limit;
		}

		public void setOutput(String output) {
			this.output = output;
		}

		public String getOutput() {
			return output;
		}

		public void setPesevekeys(String pesevekeys) {
			this.pesevekeys = pesevekeys;
		}

		public String getPesevekeys() {
			return pesevekeys;
		}

		public void setSeach(String seach) {
			this.seach = seach;
		}

		public String getSeach() {
			return seach;
		}

		public void setSeachByAny(String seachByAny) {
			this.seachByAny = seachByAny;
		}

		public String getSeachByAny() {
			return seachByAny;
		}

		public void setSeachWildcadsEnabled(String seachWildcadsEnabled) {
			this.seachWildcadsEnabled = seachWildcadsEnabled;
		}

		public String getSeachWildcadsEnabled() {
			return seachWildcadsEnabled;
		}

		public void setStatSeach(String statSeach) {
			this.statSeach = statSeach;
		}

		public String getStatSeach() {
			return statSeach;
		}

		public String[] getSortfield() {
			return sortfield;
		}

		public void setSortfield(String[] sortfield) {
			this.sortfield = sortfield;
		}

		public String[] getNodeids() {
			return nodeids;
		}

		public void setNodeids(String[] nodeids) {
			this.nodeids = nodeids;
		}

		public String[] getSortorder() {
			return sortorder;
		}

		public void setSortorder(String[] sortorder) {
			this.sortorder = sortorder;
		}

		// public HostFilter getFilter() {
		// return filter;
		// }
		// public void setFilter(HostFilter filter) {
		// this.filter = filter;
		// }
		public String[] getProxyids() {
			return proxyids;
		}

		public void setProxyids(String[] proxyids) {
			this.proxyids = proxyids;
		}

	}
}
