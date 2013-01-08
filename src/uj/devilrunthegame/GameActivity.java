package uj.devilrunthegame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class GameActivity extends SimpleBaseGameActivity
{

	@Override
	public EngineOptions onCreateEngineOptions()
	{
		camera = new Camera(0, 0, Platform.fWidth, Platform.fHeight);
		Platform.camera = camera;
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(Platform.fWidth, Platform.fHeight), camera);
	}

	@Override
	protected void onCreateResources()
	{
		Platform.assetManager = getAssets();
		Platform.textureManager = getTextureManager();
		Platform.vboManager = getVertexBufferObjectManager();
		
		level1 = new Level(getTextureManager(), getAssets(), getVertexBufferObjectManager());
		level1.initLevelResources();
		
		
	}
	ILevel level1;
	
	@Override
	protected Scene onCreateScene()
	{
		
		return level1.getScene();
	}

	
	private Camera camera;
	

}
