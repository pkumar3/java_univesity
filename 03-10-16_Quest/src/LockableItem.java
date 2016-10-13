package F;

public abstract class LockableItem extends Item {

	private boolean locked;
	private KeyType keyType;
	
	private String lockedImageFile, unlockedImageFile;
	
	
	
	public LockableItem(String lockedImageFile, 
						String unlockedImageFile,
						boolean visible, 
						boolean canPickUp,
						boolean locked,
						KeyType keyType) {
		super(locked ? lockedImageFile : unlockedImageFile, visible, canPickUp);
		this.locked = locked;
		this.keyType = keyType;
		this.lockedImageFile = lockedImageFile;
		this.unlockedImageFile = unlockedImageFile;
	}

	private String getLockedImageFile() {
		return isLocked() ? lockedImageFile : unlockedImageFile;
	}
	
	public KeyType getKeyType() 			{ return keyType;		}
	public boolean isLocked() 				{ return locked; 			}
	public void setLocked(boolean locked) 	{ 
		this.locked = locked; 
		setImage(getLockedImageFile()); 
	}

	
}
