package edu.umb.cs681.hw09;
import java.time.LocalDateTime;
import java.util.LinkedList;
public class APFS extends FileSystem implements Runnable{
	private String ownerName;
	private LocalDateTime lastModified;
	private static APFS instance = null;

	public APFS(String ownerName) {
		this.ownerName = ownerName;
		this.lastModified = LocalDateTime.now();
	}

	public APFS getInstance() {
		if (instance == null) {
			instance = new APFS(ownerName);
		}
		return instance;
	}

	@Override
	protected FSElement createDefaultRoot() {
		return new ApfsDirectory(null, "root");
	}

	public ApfsDirectory getRootDir() {
		ApfsDirectory root = (ApfsDirectory) this.getRoot();
		return root;
	}

	public void setRootDir(ApfsDirectory root) {
		super.setRoot(root);
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}

	public void run() {
		System.out.println("\nThread #" + Thread.currentThread().getId());
		System.out.println("Size of " + getRootDir().getName() + " dir: " + getRootDir().getTotalSize());
		LinkedList<ApfsElement> rootChildren = getRootDir().getChildren();
		for (ApfsElement child : rootChildren) {
			System.out.println("\n"+child.getName());
			LinkedList<ApfsElement> child1Children =child.getChildren();
			for (ApfsElement child1 : child1Children) {
				System.out.println(child1.getName());
				LinkedList<ApfsElement> child2Children =child1.getChildren();
				for (ApfsElement child2 : child2Children) {
					System.out.println(child2.getName());
				}
			}
		}
	}

	public static void main(String args[]) {

		APFS apfs = new APFS("File System of APFS");
		

		apfs.initFileSystem("Downloads", 2000);
		ApfsDirectory root = apfs.getRootDir();

		ApfsDirectory applications = new ApfsDirectory(root, "applications");
		root.appendChild(applications);
		ApfsFile a,b;
		a = new ApfsFile(applications, "a", 30);
		b = new ApfsFile(applications, "b", 10);
		applications.appendChild(a);
		applications.appendChild(b);

		Thread t1 = new Thread(apfs);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ApfsDirectory home  = new ApfsDirectory(root, "home");
		root.appendChild((home));
		ApfsFile c, d;
		c = new ApfsFile(home, "c", 10);
		d = new ApfsFile(home, "d", 20);
		home.appendChild(c);
		home.appendChild(d);
		
		ApfsDirectory code = new ApfsDirectory(home, "code");
		home.appendChild(code);
		

		Thread t2 = new Thread(apfs);
		
		try {
			t2.start();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		

		ApfsLink e, f;
		e = new ApfsLink(home, "e", applications);
		f = new ApfsLink(code, "f", a);
		home.appendChild(e);
		code.appendChild(f);

		Thread t3 = new Thread(apfs);
		try {
			t3.start();
			t3.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Root total size: " + root.getTotalSize());
		LinkedList<ApfsElement> rootChildren = root.getChildren();
		for (ApfsElement child : rootChildren) {
			System.out.println(child.getName());
		}
	}
}
