package com.jmal;

public class MALException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String additionalInfo = "";
	
	public MALException(Exception e) {
		super(e);
		assert !(e instanceof MALException) : e;
	}
	
	public MALException(String string) {
		super(string);
	}
	
	public MALException(String string, String additionalInfo) {
		this(string);
		this.setAdditionalInfo(additionalInfo);
	}
	
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}
	
	public static class Timeout extends MALException {
		private static final long serialVersionUID = 1L;
		
		public Timeout(String string) {
			super(string);
		}
	}
	
	public static class E50X extends MALException {
		private static final long serialVersionUID = 1L;
		
		public E50X(String string) {
			super(string);
		}
	}
	
	public static class E403 extends MALException {
		private static final long serialVersionUID = 1L;
		
		public E403(String string) {
			super(string);
		}
	}
	
	public static class E401 extends MALException {
		private static final long serialVersionUID = 1L;
		
		public E401(String string) {
			super(string);
		}
	}
	
	public static class E404 extends MALException {
		private static final long serialVersionUID = 1L;
		
		public E404(String string) {
			super(string);
		}
	}
	
	public static class RateLimit extends MALException {
		private static final long serialVersionUID = 1L;
		
		public RateLimit(String string) {
			super(string);
		}
	}
}
