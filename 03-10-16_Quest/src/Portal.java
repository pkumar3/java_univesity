package F;

public class Portal extends Door  {

	private String fileToLoad;
	
	public Portal(	String lockedImageFile, 
					String unlockedImageFile,
					boolean visible, 
					boolean locked, 
					KeyType keyType, 
					String fileToLoad) {
		super(lockedImageFile, unlockedImageFile, visible, locked, keyType);
		this.fileToLoad = fileToLoad;
	}
	public String getFileToLoad() { return fileToLoad; }
}
