package com.zabbix.api.domain.base;

import java.util.ArrayList;
import java.util.List;

public class ActionFilter {
	private Integer evaltype;
	private String eval_formula;
	private String formula;
	private List<ActionCondition> conditions =new ArrayList<ActionCondition>();
//	private List<ActionCondition> conditions;
	
	
	
	
	
	
	public String getEval_formula() {
		return eval_formula;
	}
	
	public List<ActionCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<ActionCondition> conditions) {
		this.conditions = conditions;
	}

	public void setEval_formula(String eval_formula) {
		this.eval_formula = eval_formula;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public Integer getEvaltype() {
		return evaltype;
	}
	public void setEvaltype(Integer evaltype) {
		this.evaltype = evaltype;
	}
//	public List<ActionCondition> getConditions() {
//		return conditions;
//	}
//	public void setConditions(List<ActionCondition> conditions) {
//		this.conditions = conditions;
//	}
	
}
