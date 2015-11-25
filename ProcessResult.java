import java.io.Serializable;

class ProcessResult<T extends Serializable> implements Serializable{

	private T data;

	public ProcessResult(T data){
		this.data=data;
	}

	public T getData(){
		return data;
	}


}