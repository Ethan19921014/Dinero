package suwa.tool;

public class IdWorker {
	/**
	 * 42位的時間字首+10位的節點標識+12位的sequence避免併發的數字（12位不夠用時強制得到新的時間字首）
	 * <p>
	 * <b>對系統時間的依賴性非常強，需要關閉ntp的時間同步功能，或者當檢測到ntp時間調整後，拒絕分配id。
	 * 
	 * @author sumory.wu
	 * @date 2012-2-26 下午6:40:28
	 */
	

	    private final long workerId;
	    private final long snsEpoch = 1330328109047L;// 起始標記點，作為基準
	    private long sequence = 0L;// 0，併發控制
	    private final long workerIdBits = 10L;// 只允許workid的範圍為：0-1023
	    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 1023,1111111111,10位
	    private final long sequenceBits = 12L;// sequence值控制在0-4095

	    private final long workerIdShift = this.sequenceBits;// 12
	    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22
	    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;// 4095,111111111111,12位

	    private long lastTimestamp = -1L;

	    public IdWorker(long workerId) {
	        super();
	        if (workerId > this.maxWorkerId || workerId < 0) {// workid < 1024[10位：2的10次方]
	            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
	        }
	        this.workerId = workerId;
	    }

	    public synchronized long nextId() throws Exception {
	        long timestamp = this.timeGen();
	        if (this.lastTimestamp == timestamp) {// 如果上一個timestamp與新產生的相等，則sequence加一(0-4095迴圈)，下次再使用時sequence是新值
	            //System.out.println("lastTimeStamp:" + lastTimestamp);
	            this.sequence = this.sequence + 1 & this.sequenceMask;
	            if (this.sequence == 0) {
	                timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
	            }
	        }
	        else {
	            this.sequence = 0;
	        }
	        if (timestamp < this.lastTimestamp) {
	            System.out.println("Clock moved backwards.Refusing to generate id for "+(this.lastTimestamp - timestamp)+"milliseconds.");
	            throw new Exception(String.format("Clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
	        }

	        this.lastTimestamp = timestamp;
	        // 生成的timestamp
	        return timestamp - this.snsEpoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
	    }

	    /**
	     * 保證返回的毫秒數在引數之後
	     * 
	     * @param lastTimestamp
	     * @return
	     */
	    private long tilNextMillis(long lastTimestamp) {
	        long timestamp = this.timeGen();
	        while (timestamp <= lastTimestamp) {
	            timestamp = this.timeGen();
	        }
	        return timestamp;
	    }

	    /**
	     * 獲得系統當前毫秒數
	     * 
	     * @return
	     */
	    private long timeGen() {
	        return System.currentTimeMillis();
	    }
	    
	    public String nextIdStr() {
	    	IdWorker idWorker = new IdWorker(1);
	    	long newIdLong;
			try {
				newIdLong = idWorker.nextId();
				String newId = String.valueOf(newIdLong);
				return newId;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "somethingWrong";
			}
			
	    }

	    public static void main(String[] args) throws Exception {
	        IdWorker iw1 = new IdWorker(1);
	        IdWorker iw2 = new IdWorker(2);
	        IdWorker iw3 = new IdWorker(3);

	        // System.out.println(iw1.maxWorkerId);
	        // System.out.println(iw1.sequenceMask);

	        System.out.println("---------------------------");

	        long workerId = 1L;
	        long twepoch = 1330328109047L;
	        long sequence = 0L;// 0
	        long workerIdBits = 10L;
	        long maxWorkerId = -1L ^ -1L << workerIdBits;// 1023,1111111111,10位
	        long sequenceBits = 12L;

	        long workerIdShift = sequenceBits;// 12
	        long timestampLeftShift = sequenceBits + workerIdBits;// 22
	        long sequenceMask = -1L ^ -1L << sequenceBits;// 4095,111111111111,12位

	        long ct = System.currentTimeMillis();// 1330328109047L;//
	        System.out.println(ct);

	        System.out.println(ct - twepoch);
	        System.out.println(ct - twepoch << timestampLeftShift);// 左移22位：*2的22次方
	        System.out.println(workerId << workerIdShift);// 左移12位：*2的12次方
	        System.out.println("哈哈");
	        System.out.println(ct - twepoch << timestampLeftShift | workerId << workerIdShift);
	        long result = ct - twepoch << timestampLeftShift | workerId << workerIdShift | sequence;// 取時間的低40位 | （小於1024:只有12位）的低12位 | 計算的sequence
	        System.out.println(result);

	        System.out.println("---------------");
	        for (int i = 0; i < 10; i++) {
	            System.out.println("IdWorker 01:"+iw1.nextId());
//	            System.out.println("IdWorker 02:"+iw2.nextId());
//	            System.out.println("IdWorker 03:"+iw3.nextId());
	        }
	    }
}
