import numpy as np

source_lines = []
target_lines = []
embeddings = {}

vector_size = 50

with open("glove_50d.txt", errors="ignore") as f:
  i = 0
  for line in f:
    i += 1
    values = line.split()
    do_this = True
    for i, val in enumerate(values[1:]):
      try:
        values[i + 1] = float(val)
      except:
        do_this = False

    if do_this:
      embeddings[values[0]] = np.array(values[1:])

    if i == 100000:
      break

with open("fullSource.txt") as f:
  with open("fullSourceEmb.txt", "w") as out:
    for line in f:
      words = line.split()

      n = 0
      word_sum = np.zeros(vector_size)
      for word in words:
        if word in embeddings:
          word_sum += embeddings[word]
          n += 1

      if n > 0:
        word_sum = word_sum / n
      for i in range(vector_size):
        out.write(str(word_sum[i]) + " ")
      out.write("\n")
