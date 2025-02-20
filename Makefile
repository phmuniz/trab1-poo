all: run diff

run:
	java src/Main.java inputs/normais/06/

diff:
	diff estatisticas-prestadores.csv inputs/normais/06/saida/2-estatisticas-prestadores.csv
	diff estatisticas-casais.csv inputs/normais/06/saida/3-estatisticas-casais.csv
	./diff_com_tolerancia.sh planejamento.csv inputs/normais/06/saida/1-planejamento.csv

clean:
	rm -rf planejamento.csv estatisticas-prestadores.csv estatisticas-casais.csv