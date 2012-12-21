package com.kamantas.mob.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kamantas.mob.R;
import com.kamantas.mob.global.Globals;
import com.kamantas.mob.model.Team;
import com.kamantas.mob.util.http.HttpGetConnector;
import com.kamantas.mob.util.http.IDataCallback;

public class TeamsActivity extends Activity implements IDataCallback {

	private static final int LOAD_TEAMS = 0;

	private static final String TAG = null;

	private ListView mTeamsList;
	private TeamsAdapter mAdapter;
	private HttpGetConnector mConnector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teams);

		mTeamsList = (ListView) findViewById(R.id.teams_list_view);
		mAdapter = new TeamsAdapter(this);
		mTeamsList.setAdapter(mAdapter);

		String url = getString(R.string.teams_url);
		mConnector = new HttpGetConnector(Globals.getInstance().getBaseUrl()
				+ url, this, LOAD_TEAMS, this);
		mConnector.execute(new BasicNameValuePair("_r", "" + Math.random()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_teams, menu);
		return true;
	}

	class TeamsAdapter extends BaseAdapter {
		private List<Team> teams;
		private LayoutInflater mInflater;

		public TeamsAdapter(Activity c) {
			super();
			mInflater = c.getLayoutInflater();
			teams = new ArrayList<Team>();
		}

		@Override
		public int getCount() {
			return teams.size();
		}

		@Override
		public Object getItem(int position) {
			return teams.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			Team team = teams.get(position);

			if (convertView == null) {
				vi = mInflater.inflate(android.R.layout.simple_list_item_2,
						null);
			}

			TextView text1 = (TextView) vi.findViewById(android.R.id.text1);
			text1.setText(team.getName());

			TextView text2 = (TextView) vi.findViewById(android.R.id.text2);
			text2.setText(team.getShortName());

			vi.setTag(team);

			return vi;
		}

		public List<Team> getTeams() {
			return teams;
		}

		public void setTeams(List<Team> teams) {
			this.teams = teams;
		}
	}

	@Override
	public void onPreExecute(int requestId, Activity ui) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPostExecute(int requestId, Activity ui, String response) {
		try {
			JSONObject resp = new JSONObject(response);
			JSONArray teams = resp.getJSONArray("teams");
			if (teams.length() > 0) {
				List<Team> loadedTeams = new ArrayList<Team>();
				for (int i = 0; i < teams.length(); i++) {
					JSONObject team = teams.getJSONObject(i);

					Team loadedTeam = new Team(team.getLong("id"),
							team.getString("name"), team.getString("sname"));
					loadedTeams.add(loadedTeam);
				}
				mAdapter.setTeams(loadedTeams);
				mAdapter.notifyDataSetChanged();
			}

		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
		}

	}
}
