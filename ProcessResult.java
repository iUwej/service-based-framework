
class ProcessResult<T implements Serializable> implements Serializable{

	private T data;

	public ProcessResult(T data){
		this.data=data;
	}

	public T getData(){
		return data;
	}


}