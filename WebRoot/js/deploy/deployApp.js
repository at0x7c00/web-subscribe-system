  function checkEquipment(){
	  var result = true;
            	var equipmentIDs = $("#equipments").val();
							if(equipmentIDs.length<=0){
							 alertMsg.error("请选择设备");
							 result = false;
							return false;
							}
				  var tips = $("#equipmentsTips").text();
					var tipsForos=$("#equipmentsOsTips").text();
					if(tips.length>0||tipsForos.length>0){
					  alertMsg.error(tips+tipsForos);
					  result = false;
                      return false;
   }
					return result;
  }
  
  function checkDeployTask(){
	  if($("#dt_basic1>tbody>tr").length==0){
			alertMsg.error("请添加部署任务");
			return false;
			}else {
				
				return true;
			}
  }
  
  function checkSoftwareNum(){
	  var result = true;
	  var softwareID = $("#softwareQuery").val();
    	if(softwareID.length<=0){
								 alertMsg.error("请选择部署软件");
								 result = false;
								return false;
								}
    	
    	return result;
	 }