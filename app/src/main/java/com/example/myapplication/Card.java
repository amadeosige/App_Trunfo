package com.example.myapplication;

public class Card {
        private String id;
        private String name;
        private String intelligence;
        private String strength;
        private String speed;
        private String durability;
        private String power;
        private String combat;
        private String occupation;
        Image imageObject;

        public Card(String id, String name, String intelligence, String strength, String speed, String durability, String power, String combat, String occupation, String image){
            this.id = id;
            this.name = name;
            this.intelligence = intelligence;
            this.strength = strength;
            this.speed = speed;
            this.durability = durability;
            this.power = power;
            this.combat = combat;
            this.occupation = occupation;
            this.imageObject = new Image(image);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

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

        public String getOccupation() {
            return occupation;
        }

        public String getImage() {
            return imageObject.url;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

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

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public void setImage(Image imageObject) {
            this.imageObject = imageObject;
        }

        public class Image {
            public Image(String image){
                this.url = image;
            }
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
