

//A Sensor class which has all the required attributes and
//getters and setters.
public class Sensor {

	// Attributes
	 int id;
	 String status;
	 int floorNum;
	 int roomNum;
	 int sLevel;
	 int cLevel;

	// Getter for id
	public int getId() {
		return id;
	}

	// Setter for id
	public void setId(int id) {
		this.id = id;
	}

	// getter for status
	public String isStatus() {
		return status;
	}

	// setter for status
	public void setStatus(String status) {
		this.status = status;
	}
//	public String getStatus() {
//		
//		return status;
//	}
	// getter for floor number
	public int getFloorNum() {
		return floorNum;
	}

	// setter for floor number
	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}

	// getter for room number
	public int getRoomNum() {
		return roomNum;
	}

	// setter for room number
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	// getter for smoke level
	public int getsLevel() {
		return sLevel;
	}

	// setter for smoke level
	public void setsLevel(int sLevel) {
		this.sLevel = sLevel;
	}

	// getter for co2 level
	public int getcLevel() {
		return cLevel;
	}

	// setter for co2 level
	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}
	
	// toString() method to get the details in the console
	@Override
	public String toString() {
		return "Sensor [id=" + id + ", status=" + status + ", floorNum=" + floorNum + ", roomNum=" + roomNum
				+ ", sLevel=" + sLevel + ", cLevel=" + cLevel + "]";
	}

}
