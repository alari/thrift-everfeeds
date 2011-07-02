package everfeeds.util

/**
 * Created by alari @ 25.01.11 17:53
 */
class Package {

  /**
   * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   * @throws ClassNotFoundException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  public static List<Class> getClasses(String packageName)
  throws ClassNotFoundException, IOException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    assert classLoader != null;
    String path = packageName.replace('.', '/');
    Enumeration<URL> resources = classLoader.getResources(path);
    List<File> dirs = new ArrayList<File>();
    while (resources.hasMoreElements()) {
      URL resource = resources.nextElement();
      String fileName = resource.getFile();
      String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
      dirs.add(new File(fileNameDecoded));
    }
    ArrayList<Class> classes = new ArrayList<Class>();
    for (File directory: dirs) {
      classes.addAll(findClasses(directory, packageName));
    }
    return classes;
  }

  /**
   * Recursive method used to find all classes in a given directory and subdirs.
   *
   * @param directory The base directory
   * @param packageName The package name for classes found inside the base directory
   * @return The classes
   * @throws ClassNotFoundException
   */
  @SuppressWarnings("unchecked")
  private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException, ExceptionInInitializerError {
    List<Class> classes = new ArrayList<Class>();
    if (!directory.exists()) {
      return classes;
    }
    File[] files = directory.listFiles();
    for (File file: files) {
      String fileName = file.getName();
      if (file.isDirectory()) {
        assert !fileName.contains(".");
        classes.addAll(findClasses(file, packageName + "." + fileName));
      } else if (fileName.endsWith(".class") && !fileName.contains('$')) {
        Class _class;
        try {
          _class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6));
        } catch (e) {
          // happen, for example, in classes, which depend on
          // Spring to inject some beans, and which fail,
          // if dependency is not fulfilled
          _class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6),
              true, Thread.currentThread().getContextClassLoader());
        }
        classes.add(_class);
      }
    }
    return classes;
  }
}