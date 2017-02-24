package com.xogrp.john.ziqtoollibrary.model;

import java.util.List;

/**
 * Created by john on 08/02/2017.
 */

public class User {

    /**
     * sessions : {"created_at":"2017-02-08T08:37:16.605Z","id":"30bd4e89-2e3f-46bd-a59c-9499dc29043f","ticket":null,"token":"400c9d07861185c677dff986774608d217552cb1aa502d82cacbbed12d4c7652","links":{"member":"bd41ce5a-fa8b-40f2-8c74-dd4bb2cb77e4"}}
     * linked : {"members":[{"action":"create","application":"planner","application_last_modified":null,"brand":"TheKnot","budget":"$15,001 - $20,000","budget_value":18000,"username":"Knottie66502885051dea3b","email":"xiaoqiang33@xogrp.com","fiance_salutation":null,"fiance_first_name":"","fiance_middle_name":null,"fiance_last_name":"","fiance_email":null,"fiance_role":"groom","salutation":null,"first_name":"hd","middle_name":null,"last_name":"","number_of_guests":"201 - 250","role":"bride","parenting_role":null,"wedding_city":null,"wedding_state":null,"colors":null,"themes":null,"status":"active","market_code":null,"id":"bd41ce5a-fa8b-40f2-8c74-dd4bb2cb77e4","legacy_user_id":"10000000000056009","phone_number":"533-333-4444","gender":null,"reset_password_token":"3e12631e-227a-4d8b-b95d-32c46e3d2675","public_favorites":null,"couple_photo_url":"https://qa-apis.xogrp.com/qa-media-api/images/","couple_photo_id":null,"created_at":"2016-12-14T07:11:56.228Z","unconfirmed_profile_attributes":["role","fiance_role","optins.TKPreEngaged"],"optins":{"TKKnotNews":false,"TKThirdPartyInfo":false,"TKStagesNews":false,"TKNewsflashNews":false,"TKKnotShopNews":false,"TKPreEngaged":false,"TNMonthlyNewsletter":false,"TNNewlyWedOffer":false,"TNRecipes":false,"TBNewsletter":false,"TBOffers":false,"TBNewsflash":false,"TBStages":false,"MemberShare":true},"wedding_date":"02/14/2019","engagement_date":null,"metadata":{}}]}
     */

    private SessionsBean sessions;
    private LinkedBean linked;

    public SessionsBean getSessions() {
        return sessions;
    }

    public void setSessions(SessionsBean sessions) {
        this.sessions = sessions;
    }

    public LinkedBean getLinked() {
        return linked;
    }

    public void setLinked(LinkedBean linked) {
        this.linked = linked;
    }

    public static class SessionsBean {
        /**
         * created_at : 2017-02-08T08:37:16.605Z
         * id : 30bd4e89-2e3f-46bd-a59c-9499dc29043f
         * ticket : null
         * token : 400c9d07861185c677dff986774608d217552cb1aa502d82cacbbed12d4c7652
         * links : {"member":"bd41ce5a-fa8b-40f2-8c74-dd4bb2cb77e4"}
         */

        private String created_at;
        private String id;
        private Object ticket;
        private String token;
        private LinksBean links;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getTicket() {
            return ticket;
        }

        public void setTicket(Object ticket) {
            this.ticket = ticket;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public static class LinksBean {
            /**
             * member : bd41ce5a-fa8b-40f2-8c74-dd4bb2cb77e4
             */

            private String member;

            public String getMember() {
                return member;
            }

            public void setMember(String member) {
                this.member = member;
            }
        }
    }

    public static class LinkedBean {
        private List<MembersBean> members;

        public List<MembersBean> getMembers() {
            return members;
        }

        public void setMembers(List<MembersBean> members) {
            this.members = members;
        }

        public static class MembersBean {
            /**
             * action : create
             * application : planner
             * application_last_modified : null
             * brand : TheKnot
             * budget : $15,001 - $20,000
             * budget_value : 18000
             * username : Knottie66502885051dea3b
             * email : xiaoqiang33@xogrp.com
             * fiance_salutation : null
             * fiance_first_name :
             * fiance_middle_name : null
             * fiance_last_name :
             * fiance_email : null
             * fiance_role : groom
             * salutation : null
             * first_name : hd
             * middle_name : null
             * last_name :
             * number_of_guests : 201 - 250
             * role : bride
             * parenting_role : null
             * wedding_city : null
             * wedding_state : null
             * colors : null
             * themes : null
             * status : active
             * market_code : null
             * id : bd41ce5a-fa8b-40f2-8c74-dd4bb2cb77e4
             * legacy_user_id : 10000000000056009
             * phone_number : 533-333-4444
             * gender : null
             * reset_password_token : 3e12631e-227a-4d8b-b95d-32c46e3d2675
             * public_favorites : null
             * couple_photo_url : https://qa-apis.xogrp.com/qa-media-api/images/
             * couple_photo_id : null
             * created_at : 2016-12-14T07:11:56.228Z
             * unconfirmed_profile_attributes : ["role","fiance_role","optins.TKPreEngaged"]
             * optins : {"TKKnotNews":false,"TKThirdPartyInfo":false,"TKStagesNews":false,"TKNewsflashNews":false,"TKKnotShopNews":false,"TKPreEngaged":false,"TNMonthlyNewsletter":false,"TNNewlyWedOffer":false,"TNRecipes":false,"TBNewsletter":false,"TBOffers":false,"TBNewsflash":false,"TBStages":false,"MemberShare":true}
             * wedding_date : 02/14/2019
             * engagement_date : null
             * metadata : {}
             */

            private String action;
            private String application;
            private Object application_last_modified;
            private String brand;
            private String budget;
            private int budget_value;
            private String username;
            private String email;
            private Object fiance_salutation;
            private String fiance_first_name;
            private Object fiance_middle_name;
            private String fiance_last_name;
            private Object fiance_email;
            private String fiance_role;
            private Object salutation;
            private String first_name;
            private Object middle_name;
            private String last_name;
            private String number_of_guests;
            private String role;
            private Object parenting_role;
            private Object wedding_city;
            private Object wedding_state;
            private Object colors;
            private Object themes;
            private String status;
            private Object market_code;
            private String id;
            private String legacy_user_id;
            private String phone_number;
            private Object gender;
            private String reset_password_token;
            private Object public_favorites;
            private String couple_photo_url;
            private Object couple_photo_id;
            private String created_at;
            private OptinsBean optins;
            private String wedding_date;
            private Object engagement_date;
            private MetadataBean metadata;
            private List<String> unconfirmed_profile_attributes;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getApplication() {
                return application;
            }

            public void setApplication(String application) {
                this.application = application;
            }

            public Object getApplication_last_modified() {
                return application_last_modified;
            }

            public void setApplication_last_modified(Object application_last_modified) {
                this.application_last_modified = application_last_modified;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getBudget() {
                return budget;
            }

            public void setBudget(String budget) {
                this.budget = budget;
            }

            public int getBudget_value() {
                return budget_value;
            }

            public void setBudget_value(int budget_value) {
                this.budget_value = budget_value;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getFiance_salutation() {
                return fiance_salutation;
            }

            public void setFiance_salutation(Object fiance_salutation) {
                this.fiance_salutation = fiance_salutation;
            }

            public String getFiance_first_name() {
                return fiance_first_name;
            }

            public void setFiance_first_name(String fiance_first_name) {
                this.fiance_first_name = fiance_first_name;
            }

            public Object getFiance_middle_name() {
                return fiance_middle_name;
            }

            public void setFiance_middle_name(Object fiance_middle_name) {
                this.fiance_middle_name = fiance_middle_name;
            }

            public String getFiance_last_name() {
                return fiance_last_name;
            }

            public void setFiance_last_name(String fiance_last_name) {
                this.fiance_last_name = fiance_last_name;
            }

            public Object getFiance_email() {
                return fiance_email;
            }

            public void setFiance_email(Object fiance_email) {
                this.fiance_email = fiance_email;
            }

            public String getFiance_role() {
                return fiance_role;
            }

            public void setFiance_role(String fiance_role) {
                this.fiance_role = fiance_role;
            }

            public Object getSalutation() {
                return salutation;
            }

            public void setSalutation(Object salutation) {
                this.salutation = salutation;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public Object getMiddle_name() {
                return middle_name;
            }

            public void setMiddle_name(Object middle_name) {
                this.middle_name = middle_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getNumber_of_guests() {
                return number_of_guests;
            }

            public void setNumber_of_guests(String number_of_guests) {
                this.number_of_guests = number_of_guests;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public Object getParenting_role() {
                return parenting_role;
            }

            public void setParenting_role(Object parenting_role) {
                this.parenting_role = parenting_role;
            }

            public Object getWedding_city() {
                return wedding_city;
            }

            public void setWedding_city(Object wedding_city) {
                this.wedding_city = wedding_city;
            }

            public Object getWedding_state() {
                return wedding_state;
            }

            public void setWedding_state(Object wedding_state) {
                this.wedding_state = wedding_state;
            }

            public Object getColors() {
                return colors;
            }

            public void setColors(Object colors) {
                this.colors = colors;
            }

            public Object getThemes() {
                return themes;
            }

            public void setThemes(Object themes) {
                this.themes = themes;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getMarket_code() {
                return market_code;
            }

            public void setMarket_code(Object market_code) {
                this.market_code = market_code;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLegacy_user_id() {
                return legacy_user_id;
            }

            public void setLegacy_user_id(String legacy_user_id) {
                this.legacy_user_id = legacy_user_id;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public Object getGender() {
                return gender;
            }

            public void setGender(Object gender) {
                this.gender = gender;
            }

            public String getReset_password_token() {
                return reset_password_token;
            }

            public void setReset_password_token(String reset_password_token) {
                this.reset_password_token = reset_password_token;
            }

            public Object getPublic_favorites() {
                return public_favorites;
            }

            public void setPublic_favorites(Object public_favorites) {
                this.public_favorites = public_favorites;
            }

            public String getCouple_photo_url() {
                return couple_photo_url;
            }

            public void setCouple_photo_url(String couple_photo_url) {
                this.couple_photo_url = couple_photo_url;
            }

            public Object getCouple_photo_id() {
                return couple_photo_id;
            }

            public void setCouple_photo_id(Object couple_photo_id) {
                this.couple_photo_id = couple_photo_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public OptinsBean getOptins() {
                return optins;
            }

            public void setOptins(OptinsBean optins) {
                this.optins = optins;
            }

            public String getWedding_date() {
                return wedding_date;
            }

            public void setWedding_date(String wedding_date) {
                this.wedding_date = wedding_date;
            }

            public Object getEngagement_date() {
                return engagement_date;
            }

            public void setEngagement_date(Object engagement_date) {
                this.engagement_date = engagement_date;
            }

            public MetadataBean getMetadata() {
                return metadata;
            }

            public void setMetadata(MetadataBean metadata) {
                this.metadata = metadata;
            }

            public List<String> getUnconfirmed_profile_attributes() {
                return unconfirmed_profile_attributes;
            }

            public void setUnconfirmed_profile_attributes(List<String> unconfirmed_profile_attributes) {
                this.unconfirmed_profile_attributes = unconfirmed_profile_attributes;
            }

            public static class OptinsBean {
            }

            public static class MetadataBean {
            }
        }
    }
}
