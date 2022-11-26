package com.example.myapplication;

public class CardClass {
        private String response;
        private String id;
        private String name;
        Powerstats PowerstatsObject;
        Image ImageObject;

        // Getter Methods
        public String getResponse() {
            return response;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Powerstats getPowerstats() {
            return PowerstatsObject;
        }

        public Image getImage() {
            return ImageObject;
        }

        // Setter Methods

        public void setResponse(String response) {
            this.response = response;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPowerstats(Powerstats powerstatsObject) {
            this.PowerstatsObject = powerstatsObject;
        }

        public void setImage(Image imageObject) {
            this.ImageObject = imageObject;
        }
    }
    class Image {
        private String url;


        // Getter Methods

        public String getUrl() {
            return url;
        }

        // Setter Methods

        public void setUrl(String url) {
            this.url = url;
        }
    }
    class Powerstats {
        private String intelligence;
        private String strength;
        private String speed;
        private String durability;
        private String power;
        private String combat;


        // Getter Methods

        public String getIntelligence() {
            return intelligence;
        }

        public String getStrength() {
            return strength;
        }

        public String getSpeed() {
            return speed;
        }

        public String getDurability() {
            return durability;
        }

        public String getPower() {
            return power;
        }

        public String getCombat() {
            return combat;
        }

        // Setter Methods

        public void setIntelligence(String intelligence) {
            this.intelligence = intelligence;
        }

        public void setStrength(String strength) {
            this.strength = strength;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public void setDurability(String durability) {
            this.durability = durability;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public void setCombat(String combat) {
            this.combat = combat;
        }
}
