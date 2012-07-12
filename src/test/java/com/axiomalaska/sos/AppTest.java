package com.axiomalaska.sos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.axiomalaska.sos.cnfaic.CnfaicObservationUpdaterFactory;
import com.axiomalaska.sos.data.Location;
import com.axiomalaska.sos.data.PublisherInfo;
import com.axiomalaska.sos.data.PublisherInfoImp;
import com.axiomalaska.sos.data.SosPhenomenon;
import com.axiomalaska.sos.data.SosSensor;
import com.axiomalaska.sos.data.SosPhenomenonImp;
import com.axiomalaska.sos.data.SosSensorImp;
import com.axiomalaska.sos.data.SosStationImp;
import com.axiomalaska.sos.data.SosStation;
import com.axiomalaska.sos.data.ObservationCollection;

public class AppTest {

	@Test
	public void test() {
		assertTrue(true);
	}

//	@Test
//	public void testCnfaic() throws Exception{
//		PublisherInfo publisherInfo = new PublisherInfoImp();
//		CnfaicObservationUpdaterFactory factory = 
//				new CnfaicObservationUpdaterFactory();
//		
//		ObservationUpdater observationUpdater = factory.buildCnfaicObservationUpdater(
//				"http://192.168.8.15:8080/sos/sos", publisherInfo);
//		
//		observationUpdater.update();
//	}
	
	@Test
	public void test3() throws Exception {
//		ObservationRetriever observationRetriever = createObservationRetriever();
//		
//		ObservationUpdater sosSensorBuilder = 
//				new ObservationUpdater("http://sos.axiomalaska.com/sos");
//		
//		Station station = createStation();
//		sosSensorBuilder.update(station, observationRetriever);
	}
	
	private String formatDate(Calendar date) {
		Calendar localDate = (Calendar) date.clone();

		// localDate.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"))

//		localDate.setTimeZone(TimeZone.getTimeZone("US/Alaska"));
		String text = localDate.get(Calendar.YEAR) + "/"
				+ (localDate.get(Calendar.MONTH) + 1) + "/"
				+ localDate.get(Calendar.DAY_OF_MONTH) + " "
				+ localDate.get(Calendar.HOUR_OF_DAY) + ":"
				+ localDate.get(Calendar.MINUTE) + " "
				+ localDate.getTimeZone().getID();
		return text;
	}
	
	private ObservationRetriever createObservationRetriever(){
		ObservationRetriever observationRetriever = new ObservationRetriever(){
			public ObservationCollection getObservationCollection(SosStation station, 
					SosSensor sensor, SosPhenomenon phenomenon, Calendar startDate){
				ObservationCollection valuesCollection = new ObservationCollection();
				
				valuesCollection.setSensor(sensor);
				valuesCollection.setStation(station);
				valuesCollection.setPhenomenon(phenomenon);
				
				List<Double> values = new ArrayList<Double>();
				values.add(10.0);
				values.add(11.0);
				values.add(12.0);
				valuesCollection.setObservationValues(values);
				
				List<Calendar> dateValues = new ArrayList<Calendar>();
				
				Calendar baseDate = Calendar.getInstance();
				baseDate.set(2012, Calendar.MAY, 7, 11, 11, 11);
				baseDate.getTime();
				
				for (int count = 0; count < 3; count++) {
					Calendar date = (Calendar)baseDate.clone();
					date.add(Calendar.HOUR_OF_DAY, -4 * count);
					dateValues.add(date);
				}
				
				valuesCollection.setObservationDates(dateValues);
				
				return valuesCollection;
			}
		};
		
		return observationRetriever;
	}
	
	private SosStation createStation(){
		
		List<SosSensor> sensors = new ArrayList<SosSensor>();
		List<SosPhenomenon> phenomena = new ArrayList<SosPhenomenon>();
		SosPhenomenonImp airTemPhenomenonDepth20 = new SosPhenomenonImp();
		airTemPhenomenonDepth20.setId("urn:x-ogc:def:phenomenon:IOOS:0.0.1:air_temperature");
		airTemPhenomenonDepth20.setName("Air Temperature");
		airTemPhenomenonDepth20.setUnits("C");
		phenomena.add(airTemPhenomenonDepth20);
		SosSensorImp airTem20Sensor = new SosSensorImp();
		airTem20Sensor.setId("Air Temperature");
		airTem20Sensor.setDescription("Air Temperature at 20 meters");
		airTem20Sensor.setPhenomena(phenomena);
		
		sensors.add(airTem20Sensor);
		
		phenomena = new ArrayList<SosPhenomenon>();
		SosPhenomenonImp airTemPhenomenonDepth10 = new SosPhenomenonImp();
		airTemPhenomenonDepth10.setId("urn:x-ogc:def:phenomenon:IOOS:0.0.1:air_temperature");
		airTemPhenomenonDepth10.setName("Air Temperature");
		airTemPhenomenonDepth10.setUnits("C");
		phenomena.add(airTemPhenomenonDepth10);
		SosSensorImp airTem10Sensor = new SosSensorImp();
		airTem10Sensor.setId("Air Temperature");
		airTem10Sensor.setDescription("Air Temperature at 10 meters");
		airTem10Sensor.setPhenomena(phenomena);
		
		sensors.add(airTem10Sensor);
		
		SosStationImp station = new SosStationImp();
		
		station.setLocation(new Location(63.0, -143.0));
		station.setFeatureOfInterestName("Sonoma House - AOOS");
		station.setId("6");
		station.setSensors(sensors);
		
		return station;
	}

}
