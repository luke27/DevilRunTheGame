package uj.devilrunthegame;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.color.Color;

import android.content.res.AssetManager;

public class Level implements ILevel
{
	public Level()
	{
		scene = new Scene();
		scene.setBackground(new Background(Color.GREEN));
	}
	
	public Level(TextureManager texMng, AssetManager assMng, VertexBufferObjectManager vbo)
	{
		scene = new Scene();
		this.texMng = texMng;
		this.assetsMng = assMng;
		this.vbo = vbo;
	}
	
	@Override
	public Scene getScene()
	{
		return scene;
	}

	Scene scene;

	@Override
	public void initLevelResources()
	{
		try
		{
			backTexture = new BitmapTexture(texMng, new IInputStreamOpener() {
				
				@Override
				public InputStream open() throws IOException
				{
					return assetsMng.open("background.png");
				}
			});
			
			
			
			backTexture.load();
			
			
			leftTouchButtonTexture = new BitmapTexture(texMng, new IInputStreamOpener() {
				
				@Override
				public InputStream open() throws IOException {
					return assetsMng.open("left.png");
				}
			});
			
			leftTouchButtonTexture.load();
			
			righTITexture = new BitmapTexture(texMng, new IInputStreamOpener() {
				
				@Override
				public InputStream open() throws IOException {
					
					return assetsMng.open("right.png");
				}
			});
			righTITexture.load();
			
		}
		catch(Exception e)
		{
			
		}
		
		background = new ParallaxBackground(0, 0, 0);
		background.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0.0f, 0.0f, new TextureRegion(backTexture, 0, 0, 800, 480), vbo)));
		
		scene.setBackground(background);
		
		pl = new Player();
		pl.init();
		scene.attachChild(pl.getSprite());
		
		setControllers();
		
	}
	
	public void setControllers()
	{
		 HUD yourHud = new HUD();
		    
		    final Sprite left = new Sprite(Platform.fWidth * 0.03f, Platform.fHeight * 0.7f, TextureRegionFactory.extractFromTexture(leftTouchButtonTexture), vbo)
		    {
		        public boolean onAreaTouched(TouchEvent touchEvent, float X, float Y)
		        {
		            if (touchEvent.isActionMove() || touchEvent.isActionDown() || touchEvent.isActionUp())
		            {
		                pl.moveLeft();
		                //android.util.Log.i("", " LEFT");
		            }
		            return true;
		        };
		    };
		    
		    final Sprite right = new Sprite(Platform.fWidth * 0.12f, Platform.fHeight * 0.7f, TextureRegionFactory.extractFromTexture(righTITexture), vbo)
		    {
		        public boolean onAreaTouched(TouchEvent touchEvent, float X, float Y)
		        {
		            if (touchEvent.isActionMove() || touchEvent.isActionDown() || touchEvent.isActionUp())
		            {
		                pl.moveRight();
		            }
		            return true;
		        };
		    };
		    
		    yourHud.registerTouchArea(left);
		    yourHud.registerTouchArea(right);
		    yourHud.attachChild(left);
		    yourHud.attachChild(right);
		    
		    Platform.camera.setHUD(yourHud);
	}
	
	Player pl;
	
	private ParallaxBackground background;
	private ITexture backTexture;
	
	private TextureManager texMng;
	private AssetManager assetsMng;
	private VertexBufferObjectManager vbo;
	
	private ITexture leftTouchButtonTexture;
	private ITexture righTITexture;
	

}
