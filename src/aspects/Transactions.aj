package aspects;

public abstract aspect Transactions {
	
	protected abstract pointcut transactioOperation();
	
	Object around(): transactioOperation(){
		
		Object ret = proceed();
		
		return ret;	
		
	}
	
	
}
