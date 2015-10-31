/* ****** GENERIC *******/
function showPopup(url,ancho,alto){
	var posicion_x; 
	var posicion_y; 
	posicion_x=(screen.width/2)-(ancho/2); 
	posicion_y=(screen.height/2)-(alto/2); 
	window.open(url, 'popup', "width="+ancho+",height="+alto+",menubar=0,statusbar=0,location=0,toolbar=0,directories=0,scrollbars=no,resizable=no,modal=yes,left="+posicion_x+",top="+posicion_y+"");
}



/* ****** STATISTICS *******/
function updateAutocomplete() {
	var checkElement = document.forms['criteriaForm']['criteriaForm:chkAll_input'];
	var autocompleteElement = document.forms['criteriaForm']['criteriaForm:txtName_input'];
	
	if(checkElement.checked==true){
		autocompleteElement.value='';
		autocompleteElement.disabled=true;
	}else{
		autocompleteElement.disabled=false;
	}
}



/* ****** GOOGLE CHART *******/

function getMatrix(dataString){
	var dataArray = dataString.split(";");
	var dataMatrix = new Array();
	
	for (var i=0; i<dataArray.length; i++){
		dataMatrix.push(dataArray[i].split(","));
	}
	
	return dataMatrix;
} 


function getTableData(dataString){
	var data = new google.visualization.DataTable();
	var dataMatrix = getMatrix(dataString);
	var dataChart = new Array();
	
	//headers
	for (var i=0; i<dataMatrix[0].length; i++){
		if(i==0){
			data.addColumn('string',dataMatrix[0][i]);
		}else{
			data.addColumn('number',dataMatrix[0][i]);
		}
	}
	
	//data
	for (var j=1; j<dataMatrix.length; j++){
		var rowData = new Array(dataMatrix[0].length);
		for (var k=0; k<dataMatrix[0].length; k++){
			if(k==0){
				rowData[k] = dataMatrix[j][k];
			}else{
				rowData[k] = Number(dataMatrix[j][k]);
			}
		}
		dataChart.push(rowData);
	}
	
	data.addRows(dataChart);
	
	return data;
}


