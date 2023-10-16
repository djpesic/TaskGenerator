from scipy.cluster.hierarchy import average, cophenet, fcluster
from scipy.spatial.distance import pdist
from scipy.cluster import hierarchy
import matplotlib.pyplot as plt
import numpy
import sys
import os


def read_file(f):
    lines = list()
    with open(f) as file:
        line = file.readline()
        while line:
            line = line.rstrip(' ')
            row = [float(s) for s in line.split(" ")]
            lines.append(row)
            line = file.readline()
    return lines


def fancy_dendrogram(*args, **kwargs):
    max_d = kwargs.pop('max_d', None)
    if max_d and 'color_threshold' not in kwargs:
        kwargs['color_threshold'] = max_d
    annotate_above = kwargs.pop('annotate_above', 0)

    ddata = hierarchy.dendrogram(*args, **kwargs)

    if not kwargs.get('no_plot', False):
        plt.xlabel('Segmenti')
        plt.ylabel('Slicnost (%)')
        for i, d, c in zip(ddata['icoord'], ddata['dcoord'], ddata['color_list']):
            x = 0.5 * sum(i[1:3])
            y = d[1]
            if y > annotate_above:
                plt.plot(x, y, 'o', c=c)
                plt.annotate("%.3g" % y, (x, y), xytext=(0, -5),
                             textcoords='offset points',
                             va='top', ha='center')
        if max_d:
            plt.axhline(y=max_d, c='k')
    return ddata


def semantic():
    # main args: simMatrix
    mat = read_file(sys.argv[1])
    mat = numpy.array(mat)
    mat = mat.flatten()
    res = average(mat)
    dnd = fancy_dendrogram(res, no_plot='true')
    lblMap = {0: 11, 1: 14, 2: 16, 3: 17, 4: 21, 5: 22, 6: 23, 7: 33, 8: 34, 9: 6, 10: 7, 11: 8}

    newlabels = [lblMap[lbl] for lbl in dnd['leaves']]

    plt.figure()

    fancy_dendrogram(
        res,
        annotate_above=60,  # useful in small plots so annotations don't overlap
        max_d=61,
        labels=newlabels
    )
    aMin, aMax = numpy.amin(mat), numpy.amax(mat)
    plt.ylim(aMin-1, aMax)

    plt.show()


def main():
    semantic()


main()
