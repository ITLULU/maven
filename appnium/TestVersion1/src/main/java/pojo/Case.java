package pojo;

public class Case {

	public String caseId;
	public String apiId;
	public String desc;
	public String params;
	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the apiId
	 */
	public String getApiId() {
		return apiId;
	}
	/**
	 * @param apiId the apiId to set
	 */
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Case [caseId=");
		builder.append(caseId);
		builder.append(", apiId=");
		builder.append(apiId);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", params=");
		builder.append(params);
		builder.append("]");
		return builder.toString();
	}
	
}
