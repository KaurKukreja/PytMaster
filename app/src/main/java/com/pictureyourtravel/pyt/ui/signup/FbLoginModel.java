package com.pictureyourtravel.pyt.ui.signup;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pictureyourtravel.pyt.ui.login.LoginModel;

import java.util.List;

public class FbLoginModel {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("name")
    @Expose

    private String name;


    @SerializedName("firstName")
    @Expose

    private String firstName;


    @SerializedName("email")
    @Expose

    private String email="";




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    @SerializedName("profilePic")
    @Expose
    private String profilePic;

    @SerializedName("runtimeLocation")
    @Expose
    private List<LoginModel.RuntimeLocation> runtimeLocation = null;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("uType")
    @Expose
    private String uType;

    @SerializedName("apiStatus")
    @Expose
    private String apiStatus;


    @SerializedName("token")
    @Expose
    private String token;






    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public FbLoginModel(String msg,Integer status, String userId, String name,String profilePic, List<LoginModel.RuntimeLocation> runtimeLocation) {
        this.status = status;
        this.userId = userId;
        this.name = name;
        this.profilePic = profilePic;
        this.msg=msg;
        this.runtimeLocation = runtimeLocation;
    }
    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Datum>getData() {
        return data;
    }

    public void setData(List<Datum>data) {
        this.data = data;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }


    public List<LoginModel.RuntimeLocation> getRuntimeLocation() {
        return runtimeLocation;
    }

    public void setRuntimeLocation(List<LoginModel.RuntimeLocation> runtimeLocation) {
        this.runtimeLocation = runtimeLocation;
    }

    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Datum {

        @SerializedName("photos")
        @Expose
        private List<Photo> photos = null;
        @SerializedName("totalPics")
        @Expose
        private Integer totalPics;
        @SerializedName("userId")
        @Expose
        private UserId userId;
        @SerializedName("imageId")
        @Expose
        private String imageId;
        @SerializedName("imgOwner")
        @Expose
        private String imgOwner;
        @SerializedName("review")
        @Expose
        private String review;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("createdOn")
        @Expose
        private String createdOn;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("imageLarge")
        @Expose
        private String imageLarge;
        @SerializedName("imageStandard")
        @Expose
        private String imageStandard;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("placeTag")
        @Expose
        private String placeTag;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("fullName")
        @Expose
        private String fullName;

        public Datum(UserId userId, String imageId, String imgOwner, String review, Integer v, String createdOn, String type, String imageLarge, String id, String imageStandard,String imageUrl, String placeTag, String country, String fullName) {
            this.userId = userId;
            this.imageId = imageId;
            this.imgOwner = imgOwner;
            this.review = review;
            this.v = v;
            this.createdOn = createdOn;
            this.type = type;
            this.imageLarge = imageLarge;
            this.id = id;
            this.imageStandard = imageStandard;
            this.imageUrl = imageUrl;
            this.placeTag = placeTag;
            this.country = country;
            this.fullName = fullName;
        }

        public UserId getUserId() {
            return userId;
        }

        public void setUserId(UserId userId) {
            this.userId = userId;
        }

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public String getImgOwner() {
            return imgOwner;
        }

        public void setImgOwner(String imgOwner) {
            this.imgOwner = imgOwner;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImageLarge() {
            return imageLarge;
        }

        public void setImageLarge(String imageLarge) {
            this.imageLarge = imageLarge;
        }

        public String getImageStandard() {
            return imageStandard;
        }

        public void setImageStandard(String imageStandard) {
            this.imageStandard = imageStandard;
        }
        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPlaceTag() {
            return placeTag;
        }

        public void setPlaceTag(String placeTag) {
            this.placeTag = placeTag;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        public Integer getTotalPics() {
            return totalPics;
        }

        public void setTotalPics(Integer totalPics) {
            this.totalPics = totalPics;
        }


    }

    public static class Photo implements Parcelable {

        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("stateId")
        @Expose
        private String stateId;
        @SerializedName("album_name")
        @Expose
        private String albumName;
        @SerializedName("prefix")
        @Expose
        private String prefix;
        @SerializedName("suffix")
        @Expose
        private String suffix;

        @SerializedName("photoId")
        @Expose
        private String photoId;
        @SerializedName("imageLarge")
        @Expose
        private String imageLarge;
        @SerializedName("userLiked")
        @Expose
        private List<String> userLiked = null;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("dataType")
        @Expose
        private Integer dataType;


        @SerializedName("imageThumb")
        @Expose
        private String imageThumb;

        @SerializedName("access")
        @Expose
        private String access;

        protected Photo(Parcel in) {
            user = in.readParcelable(User.class.getClassLoader());
            stateId = in.readString();
            albumName = in.readString();
            prefix = in.readString();
            suffix = in.readString();
            photoId = in.readString();
            imageLarge = in.readString();
            userLiked = in.createStringArrayList();
            source = in.readString();
            if (in.readByte() == 0) {
                dataType = null;
            } else {
                dataType = in.readInt();
            }
            imageThumb = in.readString();
            access = in.readString();
            description = in.readString();
            location = in.readString();
            locationId = in.readString();
            city = in.readString();
            state = in.readString();
            country = in.readString();
            countryId = in.readString();
            category = in.createTypedArrayList(Category.CREATOR);
            if (in.readByte() == 0) {
                likeCount = null;
            } else {
                likeCount = in.readInt();
            }
            placeTag = in.readString();
            latitude = in.readDouble();
            longitude = in.readDouble();
            cityId = in.readString();
        }

        public static final Creator<Photo> CREATOR = new Creator<Photo>() {
            @Override
            public Photo createFromParcel(Parcel in) {
                return new Photo(in);
            }

            @Override
            public Photo[] newArray(int size) {
                return new Photo[size];
            }
        };

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getStateId() {
            return stateId;
        }

        public void setStateId(String stateId) {
            this.stateId = stateId;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        @SerializedName("description")

        @Expose
        private String description;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("locationId")
        @Expose
        private String locationId;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("countryId")
        @Expose
        private String countryId;
        @SerializedName("category")
        @Expose
        private List<Category> category = null;
        @SerializedName("likeCount")
        @Expose
        private Integer likeCount;
        @SerializedName("placeTag")
        @Expose
        private String placeTag;
        @SerializedName("latitude")
        @Expose
        private double  latitude;
        @SerializedName("longitude")
        @Expose
        private double   longitude;
        @SerializedName("cityId")
        @Expose
        private String cityId;


        public Photo() {
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }


        public String getPhotoId() {
            return photoId;
        }

        public void setPhotoId(String photoId) {
            this.photoId = photoId;
        }

        public String getImageLarge() {
            return imageLarge;
        }

        public void setImageLarge(String imageLarge) {
            this.imageLarge = imageLarge;
        }

        public List<String> getUserLiked() {
            return userLiked;
        }

        public void setUserLiked(List<String> userLiked) {
            this.userLiked = userLiked;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Integer getDataType() {
            return dataType;
        }

        public void setDataType(Integer dataType) {
            this.dataType = dataType;
        }

        public String getImageThumb() {
            return imageThumb;
        }

        public void setImageThumb(String imageThumb) {
            this.imageThumb = imageThumb;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLocationId() {
            return locationId;
        }

        public void setLocationId(String locationId) {
            this.locationId = locationId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public List<Category> getCategory() {
            return category;
        }

        public String getAccess() {
            return access;
        }

        public void setAccess(String access) {
            this.access = access;
        }



        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public Integer getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Integer likeCount) {
            this.likeCount = likeCount;
        }

        public String getPlaceTag() {
            return placeTag;
        }

        public void setPlaceTag(String placeTag) {
            this.placeTag = placeTag;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(user, i);
            parcel.writeString(stateId);
            parcel.writeString(albumName);
            parcel.writeString(prefix);
            parcel.writeString(suffix);
            parcel.writeString(photoId);
            parcel.writeString(imageLarge);
            parcel.writeStringList(userLiked);
            parcel.writeString(source);
            if (dataType == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeInt(dataType);
            }
            parcel.writeString(imageThumb);
            parcel.writeString(access);
            parcel.writeString(description);
            parcel.writeString(location);
            parcel.writeString(locationId);
            parcel.writeString(city);
            parcel.writeString(state);
            parcel.writeString(country);
            parcel.writeString(countryId);
            parcel.writeTypedList(category);
            if (likeCount == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeInt(likeCount);
            }
            parcel.writeString(placeTag);
            parcel.writeDouble(latitude);
            parcel.writeDouble(longitude);
            parcel.writeString(cityId);
        }
    }







    public class User implements Parcelable{

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("picture")
        @Expose
        private String picture;
        @SerializedName("id")
        @Expose
        private String idd;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("photo")
        @Expose
        private Photo photo;
        @SerializedName("lastName")
        @Expose
        private String lastName;

        protected User(Parcel in) {
            id = in.readString();
            email = in.readString();
            name = in.readString();
            picture = in.readString();
            idd = in.readString();
            firstName = in.readString();
            gender = in.readString();
            photo = in.readParcelable(Photo.class.getClassLoader());
            lastName = in.readString();
        }

        public  final Creator<User> CREATOR = new Creator<User>() {
            @Override
            public User createFromParcel(Parcel in) {
                return new User(in);
            }

            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getIdd() {
            return idd;
        }

        public void setIdd(String idd) {
            this.idd = idd;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Photo getPhoto() {
            return photo;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @SerializedName("pytPosted")
        @Expose
        private List<PytPosted> pytPosted = null;



        public List<PytPosted> getPytPosted() {
            return pytPosted;
        }

        public void setPytPosted(List<PytPosted> pytPosted) {
            this.pytPosted = pytPosted;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(id);
            parcel.writeString(email);
            parcel.writeString(name);
            parcel.writeString(picture);
            parcel.writeString(idd);
            parcel.writeString(firstName);
            parcel.writeString(gender);
            parcel.writeParcelable(photo, i);
            parcel.writeString(lastName);
        }
    }
    public class PytPosted {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("placeTag")
        @Expose
        private String placeTag;
        @SerializedName("longitude")
        @Expose
        private Double longitude;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("imageThumb")
        @Expose
        private String imageThumb;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("imageLarge")
        @Expose
        private String imageLarge;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("cityId")
        @Expose
        private String cityId;
        @SerializedName("stateId")
        @Expose
        private String stateId;
        @SerializedName("countryId")
        @Expose
        private String countryId;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("keyWords")
        @Expose
        private List<Object> keyWords = null;
        @SerializedName("category")
        @Expose
        private List<Category> category = null;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("albumName")
        @Expose
        private String albumName;
        @SerializedName("fromDes")
        @Expose
        private Integer fromDes;
        @SerializedName("coord")
        @Expose
        private List<Double> coord = null;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("dataType")
        @Expose
        private Integer dataType;
        @SerializedName("reviews")
        @Expose
        private List<Object> reviews = null;
        @SerializedName("userLiked")
        @Expose
        private List<String> userLiked = null;
        @SerializedName("modify")
        @Expose
        private List<Object> modify = null;
        @SerializedName("created_on")
        @Expose
        private String createdOn;
        @SerializedName("deleted")
        @Expose
        private Boolean deleted;
        @SerializedName("varified")
        @Expose
        private Boolean varified;
        @SerializedName("blocked")
        @Expose
        private Boolean blocked;
        @SerializedName("access")
        @Expose
        private String access;
        @SerializedName("dbCity")
        @Expose
        private String dbCity;
        @SerializedName("dbState")
        @Expose
        private String dbState;
        @SerializedName("dbCountry")
        @Expose
        private String dbCountry;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPlaceTag() {
            return placeTag;
        }

        public void setPlaceTag(String placeTag) {
            this.placeTag = placeTag;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public String getImageThumb() {
            return imageThumb;
        }

        public void setImageThumb(String imageThumb) {
            this.imageThumb = imageThumb;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImageLarge() {
            return imageLarge;
        }

        public void setImageLarge(String imageLarge) {
            this.imageLarge = imageLarge;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getStateId() {
            return stateId;
        }

        public void setStateId(String stateId) {
            this.stateId = stateId;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public List<Object> getKeyWords() {
            return keyWords;
        }

        public void setKeyWords(List<Object> keyWords) {
            this.keyWords = keyWords;
        }

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        public Integer getFromDes() {
            return fromDes;
        }

        public void setFromDes(Integer fromDes) {
            this.fromDes = fromDes;
        }

        public List<Double> getCoord() {
            return coord;
        }

        public void setCoord(List<Double> coord) {
            this.coord = coord;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Integer getDataType() {
            return dataType;
        }

        public void setDataType(Integer dataType) {
            this.dataType = dataType;
        }

        public List<Object> getReviews() {
            return reviews;
        }

        public void setReviews(List<Object> reviews) {
            this.reviews = reviews;
        }

        public List<String> getUserLiked() {
            return userLiked;
        }

        public void setUserLiked(List<String> userLiked) {
            this.userLiked = userLiked;
        }

        public List<Object> getModify() {
            return modify;
        }

        public void setModify(List<Object> modify) {
            this.modify = modify;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        public Boolean getVarified() {
            return varified;
        }

        public void setVarified(Boolean varified) {
            this.varified = varified;
        }

        public Boolean getBlocked() {
            return blocked;
        }

        public void setBlocked(Boolean blocked) {
            this.blocked = blocked;
        }

        public String getAccess() {
            return access;
        }

        public void setAccess(String access) {
            this.access = access;
        }

        public String getDbCity() {
            return dbCity;
        }

        public void setDbCity(String dbCity) {
            this.dbCity = dbCity;
        }

        public String getDbState() {
            return dbState;
        }

        public void setDbState(String dbState) {
            this.dbState = dbState;
        }

        public String getDbCountry() {
            return dbCountry;
        }

        public void setDbCountry(String dbCountry) {
            this.dbCountry = dbCountry;
        }

    }

    public static class Category implements Parcelable {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("displayName")
        @Expose
        private String displayName;

        public Category(String id, String displayName) {
            this.id = id;
            this.displayName = displayName;
        }

        protected Category(Parcel in) {
            id = in.readString();
            displayName = in.readString();
        }

        public static final Creator<Category> CREATOR = new Creator<Category>() {
            @Override
            public Category createFromParcel(Parcel in) {
                return new Category(in);
            }

            @Override
            public Category[] newArray(int size) {
                return new Category[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(displayName);
        }
    }

    public class Result {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")

        @Expose
        private String name;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl="";
        @SerializedName("searchedToday")
        @Expose
        private Integer searchedToday;
        @SerializedName("type")
        @Expose
        private String type;


        public Result(String id, String fullName, String type,String  imageUrl) {
            this.id = id;
            this.fullName = fullName;
            this.type = type;
            this.imageUrl = imageUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getSearchedToday() {
            return searchedToday;
        }

        public void setSearchedToday(Integer searchedToday) {
            this.searchedToday = searchedToday;
        }
    }


    public class UserId implements Parcelable {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("picture")
        @Expose
        private String picture;
        @SerializedName("userType")
        @Expose
        private String userType;

        public UserId(Parcel in) {
            id = in.readString();
            name = in.readString();
            email = in.readString();
            picture = in.readString();
            userType=in.readString();
        }

        public  final Creator<UserId> CREATOR = new Creator<UserId>() {
            @Override
            public UserId createFromParcel(Parcel in) {
                return new UserId(in);
            }

            @Override
            public UserId[] newArray(int size) {
                return new UserId[size];
            }
        };

        public UserId() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(email);
            dest.writeString(picture);
            dest.writeString(userType);
        }
    }


}
