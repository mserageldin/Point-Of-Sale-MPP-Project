package dataaccess;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

public class DataAccess {

	private static String pathSeparator = System.getProperty("file.separator");
	private static String path = String.join(pathSeparator, "src", "dataaccess", "storage");
	public static final String STORAGE_DIR = String.join(pathSeparator, System.getProperty("user.dir"), path);

	DataAccess() {
	}

	public <K, T> HashMap<K, T> getData(Class<T> cls) {
		Optional<HashMap<K, T>> data = readFromStorage(cls.getName());
		return data.orElse(new HashMap<K, T>());
	}

	public <K, T extends IDataAccessable<K>> void saveRecord(T record) {
		@SuppressWarnings("unchecked")
		Class<T> recordClass = (Class<T>) record.getClass();
		HashMap<K, T> data = getData(recordClass);
		data.put(record.getPrimaryKeyValue(), record);
		saveToStorage(recordClass.getName(), data);
	}

	public <K, T extends IDataAccessable<K>> void removeRecord(T record) {
		@SuppressWarnings("unchecked")
		Class<T> recordClass = (Class<T>) record.getClass();
		HashMap<K, T> data = getData(recordClass);
		data.remove(record.getPrimaryKeyValue());
		saveToStorage(recordClass.getName(), data);
	}

	public <K, T extends IDataAccessable<K>> boolean contains(Class<T> cls, K recordKey) {
		HashMap<K, T> data = getData(cls);
		return data.containsKey(recordKey);
	}

	public <T extends IDataAccessable<Integer>> int getNextKey(Class<T> cls) {
		HashMap<Integer, T> data = getData(cls);
		if (data.size() == 0) {
			return 1;
		}
		return Collections.max(data.keySet()) + 1;
	}

	@SuppressWarnings("unchecked")
	public static <K, T> Optional<HashMap<K, T>> readFromStorage(String storageName) {
		var path = FileSystems.getDefault().getPath(STORAGE_DIR, storageName);
		if (Files.isReadable(path)) {
			try (var stream = new ObjectInputStream(Files.newInputStream(path))) {
				return Optional.of((HashMap<K, T>) stream.readObject());
			} catch (Exception e) {
				e.printStackTrace();
				Arrays.asList(e.getSuppressed()).forEach(s -> s.printStackTrace());
			}
		}
		return Optional.empty();
	}

	public static <K, T> void saveToStorage(String storageName, HashMap<K, T> data) {
		var path = FileSystems.getDefault().getPath(STORAGE_DIR, storageName);
		try (var stream = new ObjectOutputStream(Files.newOutputStream(path))) {
			stream.writeObject(data);
		} catch (Exception e) {
			e.printStackTrace();
			Arrays.asList(e.getSuppressed()).forEach(s -> s.printStackTrace());
		}
	}
}
