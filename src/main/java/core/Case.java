package core;

import lombok.Data;

@Data
public class Case {

	private Agent agent;

	/**
	 * True if the case doesn't contain an agent.
	 * 
	 * @return
	 */
	public boolean isVide() {
		return agent == null;
	}

	/**
	 * True if the case contains an agent.
	 * 
	 * @return
	 */
	public boolean isNotVide() {
		return !isVide();
	}

}
