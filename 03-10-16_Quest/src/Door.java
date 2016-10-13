package F;

public class Door extends LockableItem  {

	public Door(String lockedImageFile, 
				String unlockedImageFile,
				boolean visible,
				boolean locked, 
				KeyType keyType) {
		super(lockedImageFile, unlockedImageFile, visible, false, locked, keyType);
		
	}

	

	
	
}
