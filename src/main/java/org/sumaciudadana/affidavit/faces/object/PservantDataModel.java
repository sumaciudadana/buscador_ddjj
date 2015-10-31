package org.sumaciudadana.affidavit.faces.object;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import org.sumaciudadana.affidavit.entity.Pservant;


public class PservantDataModel extends ListDataModel<Pservant> implements SelectableDataModel<Pservant>{
	
	public PservantDataModel(){
		
	}
	
	public PservantDataModel(List<Pservant> data){
		super(data);
	}

	@Override
	public Pservant getRowData(String rowKey) {
		List<Pservant> pservants = (List<Pservant>) getWrappedData();
		
		for (Pservant pservant : pservants) {
			if (pservant.getIdpservant() == Integer.parseInt(rowKey)) {
				return pservant;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Pservant pservant) {
		return pservant.getIdpservant();
	}

}
