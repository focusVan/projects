import unittest

class StringReplaceTestCase(unittest.TestCase):

    def testDemo(self):
        source = "a"
        expect = "a"
        self.assertEqual(expect, source)

    def testDemo_2(self):
        source = "HELLO"
        expect = "HELLO"
        self.assertEqual(expect, source)


