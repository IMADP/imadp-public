package com.tracktacular.service.tracker.game;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.common.collect.Lists;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * GameServiceImpl
 *
 * The standard implementation of the GameService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public final class GameServiceImpl extends PersistableUserServiceImpl<Game> implements GameService {
	private String steamKey;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(steamKey);
	}

	@Override
	public List<Game> getSteamGames(User user, String steamId) {
		List<String> gameNames;

		try
		{
			gameNames = getSteamGameNames(steamId);
		}
		catch (Exception e)
		{
			return Collections.emptyList();
		}

		List<Game> steamGames = Lists.newArrayListWithExpectedSize(gameNames.size());

		for(String gameName : gameNames)
		{
			if(StringUtils.isBlank(gameName))
				continue;

			Game game = new Game(user);
			game.setTitle(gameName);
			game.setPlatform("PC");
			game.setTag("steam");
			steamGames.add(game);
		}

		return steamGames;
	}

	/**
	 * Provides the steam uri to retrieve games.
	 *
	 * @param steamId
	 * @return URI
	 * @throws URISyntaxException
	 */
	private URI getSteamUri(String steamId) throws URISyntaxException {
		return new URIBuilder()
			.setScheme("http")
			.setHost("api.steampowered.com")
			.setPath("/IPlayerService/GetOwnedGames/v0001")
		    .setParameter("include_appinfo", "1")
		    .setParameter("include_played_free_games", "1")
		    .setParameter("key", steamKey)
		    .setParameter("steamid", steamId)
			.setParameter("format", "json").build();
	}

	/**
	 * Provides the steam input stream response.
	 *
	 * @param steamId
	 * @return InputStream
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private InputStream getSteamInputStream(String steamId) throws URISyntaxException, ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(getSteamUri(steamId));
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();

		if(httpEntity == null)
			return null;

		return httpEntity.getContent();
	}

	/**
	 * Returns a list of games associated with a steam account.
	 *
	 * @param steamId
	 * @return List<String>
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private List<String> getSteamGameNames(String steamId) throws ClientProtocolException, IOException, URISyntaxException {
		List<String> games = Lists.newArrayList();
		InputStream inputStream = getSteamInputStream(steamId);

		if(inputStream == null)
			return games;

		JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

		try
		{
			// parse the response
			jsonReader.beginObject();

			while(jsonReader.hasNext())
			{
				// parse the response object
				if("response".equalsIgnoreCase(jsonReader.nextName()))
				{
					jsonReader.beginObject();

					while(jsonReader.hasNext())
					{
						// parse the games array
						if("games".equalsIgnoreCase(jsonReader.nextName()) && jsonReader.peek() != JsonToken.NULL)
						{
							jsonReader.beginArray();

							while(jsonReader.hasNext())
							{
								jsonReader.beginObject();

								while(jsonReader.hasNext())
								{
									if("name".equalsIgnoreCase(jsonReader.nextName()))
										games.add(jsonReader.nextString());

									else
										jsonReader.skipValue();
								}

								jsonReader.endObject();
							}

							jsonReader.endArray();
						}

						else
							jsonReader.skipValue();

					}
					jsonReader.endObject();
				}

				else
					jsonReader.skipValue();
			}
		}
		finally
		{
			jsonReader.close();
		}

		return games;
	}

	// getters and setters
	public String getSteamKey() {
		return steamKey;
	}

	public void setSteamKey(String steamKey) {
		this.steamKey = steamKey;
	}

}