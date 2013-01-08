package uj.devilrunthegame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.debug.Debug;

public class Player extends Character
{
	public Player()
	{
		position.x = 0.0f;
		position.y = Platform.fHeight * 0.7f;
	}
	
	
	public void init()
	{
		buildableBitmapTextureAtlas = new BuildableBitmapTextureAtlas(Platform.textureManager, 512, 256);
		playerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buildableBitmapTextureAtlas, Platform.assetManager, "tex/snapdragon_tiled.png", 4, 3);
		
		try
		{
			buildableBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
			buildableBitmapTextureAtlas.load();
		}
		catch(Exception e)
		{
			Debug.e("Error whe load " + e.getMessage());
		}
		
		sprite = new AnimatedSprite(position.x, position.y, playerTextureRegion, Platform.vboManager);
		sprite.animate(100);
	}
	
	public AnimatedSprite getSprite()
	{
		return sprite;
	}
	
	public void moveLeft()
	{
		position.x -= 10.0f;
		sprite.setPosition(position.x, position.y);
	}
	
	public void moveRight()
	{
		position.x += 10.0f;
		sprite.setPosition(position.x, position.y);
	}
	
	private TiledTextureRegion playerTextureRegion;
	private BuildableBitmapTextureAtlas buildableBitmapTextureAtlas;
	private AnimatedSprite sprite;
}
