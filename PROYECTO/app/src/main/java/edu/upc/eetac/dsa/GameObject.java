package edu.upc.eetac.dsa;

class GameObject {
    String gameObjId;
    String description;

    public GameObject(){}

    public GameObject(String gameObjId, String description) {
        this.gameObjId = gameObjId;
        this.description = description;
    }


    public String getGameObjId() {
        return gameObjId;
    }

    public void setGameObjId(String gameObjId) {
        this.gameObjId = gameObjId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
