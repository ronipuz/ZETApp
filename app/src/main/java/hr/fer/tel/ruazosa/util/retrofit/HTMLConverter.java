package hr.fer.tel.ruazosa.util.retrofit;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.fer.tel.ruazosa.model.Tram;
import hr.fer.tel.ruazosa.util.parser.Detalji;
import hr.fer.tel.ruazosa.util.parser.Parser;
import hr.fer.tel.ruazosa.util.parser.Raspored;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;


/**
* Arbiter for converting objects to and from their representation in HTTP.
*
*/
public class HTMLConverter implements Converter {

	private Parser parser = new Parser();
	private static Scanner s;
	
	/**
	* Convert an HTTP response body to a concrete object of the specified type.
	*
	* @param body HTTP response body.
	* @param type Target object type.
	* @return Instance of {@code type} which will be cast by the caller.
	* @throws retrofit.converter.ConversionException if conversion was unable to complete. This will trigger a call to
	* {@link retrofit.Callback#failure(retrofit.RetrofitError)} or throw a
	* {@link retrofit.RetrofitError}. The exception message should report all necessary information
	* about its cause as the response body will be set to {@code null}.
	*/
	@Override
	public Object fromBody(TypedInput body, Type type)
			throws ConversionException {
		
		//if(type == Trams.class) {
		//	return parser.parseTramLines(body.in());
		//}
		String htmlSource = null;
		try {
			htmlSource = convertStreamToString(body.in());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		s.close();
		
		if(type.toString().contains("Tram")) {
			List<Tram> trams = parser.parseTramLines(htmlSource);
			return trams;
		}
		if(type.toString().contains("Raspored")) {
			Raspored raspored = parser.parseRaspored(htmlSource);
			return raspored;
		}
		if(type.toString().contains("Detalji")) {
			Detalji detalji = parser.parseDetalji(htmlSource);
			return detalji;
		}
		
		return "";
	}
	
	static String convertStreamToString(InputStream is) {
	    s = new Scanner(is, "utf-8").useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

	/**
	* Convert an object to an appropriate representation for HTTP transport.
	*
	* @param object Object instance to convert.
	* @return Representation of the specified object as bytes.
	*/
	@Override
	public TypedOutput toBody(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
