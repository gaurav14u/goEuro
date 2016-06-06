package com.goeuro.testapp.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "coreCountry"})
public class CityInfo
{
    private String coreCountry;

    private GeoPosition geo_position;

    private String distance;

    private String _id;

    private String inEurope;

    private String name;

    private String countryCode;

    private String iata_airport_code;

    private String fullName;

    private String type;

    private String key;

    private String location_id;

    private String country;

	public String getCoreCountry() {
		return coreCountry;
	}

	public void setCoreCountry(String coreCountry) {
		this.coreCountry = coreCountry;
	}

	public GeoPosition getGeo_position() {
		return geo_position;
	}

	public void setGeo_position(GeoPosition geo_position) {
		this.geo_position = geo_position;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getInEurope() {
		return inEurope;
	}

	public void setInEurope(String inEurope) {
		this.inEurope = inEurope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getIata_airport_code() {
		return iata_airport_code;
	}

	public void setIata_airport_code(String iata_airport_code) {
		this.iata_airport_code = iata_airport_code;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CountryInfo [coreCountry=" + coreCountry + ", distance="
				+ distance + ", _id=" + _id + ", inEurope=" + inEurope
				+ ", name=" + name + ", countryCode=" + countryCode
				+ ", iata_airport_code=" + iata_airport_code + ", fullName="
				+ fullName + ", type=" + type + ", key=" + key
				+ ", location_id=" + location_id + ", country=" + country + "]";
	}

    
}