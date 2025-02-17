all: run diff

run:
	java src/Main.java inputs/excecoes/EXC08/

diff:
	diff planejamento.csv inputs/excecoes/EXC08/saida/1-planejamento.csv
	diff estatisticas-prestadores.csv inputs/excecoes/EXC08/saida/2-estatisticas-prestadores.csv
	diff estatisticas-casais.csv inputs/excecoes/EXC08/saida/3-estatisticas-casais.csv

clean:
	rm -rf planejamento.csv estatisticas-prestadores.csv estatisticas-casais.csv