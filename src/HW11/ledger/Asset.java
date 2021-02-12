package HW11.ledger;

public class Asset extends Account {

	public Asset(String name) {
		super(name);
	}

	@Override
	public void credit(int value) {
		this.value -= value;
	}

	@Override
	public void debit(int value) {
		this.value += value;
	}
}
